#include <iostream>
#include <string>
#include <fstream>
#include "book.pb.h"

#include "jsoncpp/json/json.h"
#include "jsoncpp/json/value.h"
#include "jsoncpp/json/writer.h"

using namespace std;

void serialize_message(const google::protobuf::Message& message, Json::Value& value);

void fillValue(const google::protobuf::Reflection *reflection,const google::protobuf::Message &message,const google::protobuf::FieldDescriptor *field,Json::Value &value){
 
  if(field->is_repeated()){
    switch (field->cpp_type()){
#define XX(cpptype, method, valuetype, jsontype) \
      case google::protobuf::FieldDescriptor::CPPTYPE_##cpptype: { \
        int size = reflection->FieldSize(message, field); \
        for(int n = 0; n < size; ++n) { \
            value.append((jsontype)reflection->GetRepeated##method(message, field, n)); \
        } \
        break; \
      }
      XX(INT32, Int32, int32_t, Json::Int);
      XX(UINT32, UInt32, uint32_t, Json::UInt);
      XX(FLOAT, Float, float, double);
      XX(DOUBLE, Double, double, double);
      XX(BOOL, Bool, bool, bool);
      XX(INT64, Int64, int64_t, Json::Int64);
      XX(UINT64, UInt64, uint64_t, Json::UInt64);
#undef XX
      case google::protobuf::FieldDescriptor::CPPTYPE_ENUM: {
        int size = reflection->FieldSize(message, field);
        for(int n = 0; n < size; ++n) {
          value.append(reflection->GetRepeatedEnum(message, field, n)->name());
        }
        break;
      }
      case google::protobuf::FieldDescriptor::CPPTYPE_STRING: {
        int size = reflection->FieldSize(message, field);
        for(int n = 0; n < size; ++n) {
          value.append(reflection->GetRepeatedString(message, field, n));
        }
        break;
      }
      case google::protobuf::FieldDescriptor::CPPTYPE_MESSAGE: {
        int size = reflection->FieldSize(message, field);
        for(int n = 0; n < size; ++n) {
            Json::Value vv;
            serialize_message(reflection->GetRepeatedMessage(message, field, n), vv);
            if(vv.isNull()){
              vv=Json::objectValue;
            }
            value.append(vv);
        }
        break;
      }
    }
  }else{
    switch (field->cpp_type()){
#define XX(cpptype, method, valuetype, jsontype) \
      case google::protobuf::FieldDescriptor::CPPTYPE_##cpptype: { \
          value=(jsontype)reflection->Get##method(message, field); \
          break; \
      }
      XX(INT32, Int32, int32_t, Json::Int);
      XX(UINT32, UInt32, uint32_t, Json::UInt);
      XX(FLOAT, Float, float, double);
      XX(DOUBLE, Double, double, double);
      XX(BOOL, Bool, bool, bool);
      XX(INT64, Int64, int64_t, Json::Int64);
      XX(UINT64, UInt64, uint64_t, Json::UInt64);
#undef XX
      case google::protobuf::FieldDescriptor::CPPTYPE_ENUM: {
        value=reflection->GetEnum(message, field)->name();
        break;
      }
      case google::protobuf::FieldDescriptor::CPPTYPE_STRING: {
        value=reflection->GetString(message, field);
        break;
      }
      case google::protobuf::FieldDescriptor::CPPTYPE_MESSAGE: {
        serialize_message(reflection->GetMessage(message, field), value[field->name()]);
        break;
      }
    }
  }
}

void fillJson(const Json::Value &value,Json::Value &father,const std::string &key,const google::protobuf::Message &message){
  auto keys = value.getMemberNames();
  for(Json::Value::Members::iterator it = keys.begin();it != keys.end(); ++it) {  
    std::string _key = *it;
    Json::Value child;
    auto reflection = message.GetReflection(); 
    auto field = message.GetDescriptor()->FindFieldByName(_key);
    if(field==nullptr){
      father[_key]="字段不存在";
    }else if(field->is_repeated()&&!reflection->FieldSize(message, field)) {
      father[_key]="repeated 值为空";
    }else if(field->is_optional()&&!reflection->HasField(message, field)){
      father[_key]="optional 值为空";
    }else{
      auto _value = value[_key];
      if(_value.isInt()){
        fillValue(reflection,message,field,child);
      }else if(_value.isObject()){
        if(field->is_repeated()){
          int size = reflection->FieldSize(message, field);
          for(int i=0;i<size;i++){
            Json::Value _child;
            fillJson(_value,_child,_key,reflection->GetRepeatedMessage(message,field,i));
            child.append(_child);
          }
        }else{
          fillJson(_value,child,_key,reflection->GetMessage(message,field));
        }
      }
      father[_key]=child;
    }
  }
}

void serialize_message(const google::protobuf::Message& message, Json::Value& value) {
  const google::protobuf::Descriptor* descriptor = message.GetDescriptor();
  const google::protobuf::Reflection* reflection = message.GetReflection();

  for(int i = 0; i < descriptor->field_count(); ++i) {
    const google::protobuf::FieldDescriptor* field = descriptor->field(i);

    if(field->is_repeated()) {
      if(!reflection->FieldSize(message, field)) {
        continue;
      }
    } else {
      if(!reflection->HasField(message, field)) {
        continue;
      }
    }

    if(field->is_repeated()) {
      switch(field->cpp_type()) {
#define XX(cpptype, method, valuetype, jsontype) \
        case google::protobuf::FieldDescriptor::CPPTYPE_##cpptype: { \
          int size = reflection->FieldSize(message, field); \
          for(int n = 0; n < size; ++n) { \
              value[field->name()].append((jsontype)reflection->GetRepeated##method(message, field, n)); \
          } \
          break; \
        }
        XX(INT32, Int32, int32_t, Json::Int);
        XX(UINT32, UInt32, uint32_t, Json::UInt);
        XX(FLOAT, Float, float, double);
        XX(DOUBLE, Double, double, double);
        XX(BOOL, Bool, bool, bool);
        XX(INT64, Int64, int64_t, Json::Int64);
        XX(UINT64, UInt64, uint64_t, Json::UInt64);
#undef XX
        case google::protobuf::FieldDescriptor::CPPTYPE_ENUM: {
          int size = reflection->FieldSize(message, field);
          for(int n = 0; n < size; ++n) {
              value[field->name()].append(reflection->GetRepeatedEnum(message, field, n)->number());
          }
          break;
        }
        case google::protobuf::FieldDescriptor::CPPTYPE_STRING: {
          int size = reflection->FieldSize(message, field);
          for(int n = 0; n < size; ++n) {
              value[field->name()].append(reflection->GetRepeatedString(message, field, n));
          }
          break;
        }
        case google::protobuf::FieldDescriptor::CPPTYPE_MESSAGE: {
          int size = reflection->FieldSize(message, field);
          for(int n = 0; n < size; ++n) {
              Json::Value vv;
              serialize_message(reflection->GetRepeatedMessage(message, field, n), vv);
              if(vv.isNull()){
                vv=Json::objectValue;
              }
              value[field->name()].append(vv);
          }
          break;
        }
      }
      continue;
    }

    switch(field->cpp_type()) {
#define XX(cpptype, method, valuetype, jsontype) \
      case google::protobuf::FieldDescriptor::CPPTYPE_##cpptype: { \
        value[field->name()] = (jsontype)reflection->Get##method(message, field); \
        break; \
      }
      XX(INT32, Int32, int32_t, Json::Int);
      XX(UINT32, UInt32, uint32_t, Json::UInt);
      XX(FLOAT, Float, float, double);
      XX(DOUBLE, Double, double, double);
      XX(BOOL, Bool, bool, bool);
      XX(INT64, Int64, int64_t, Json::Int64);
      XX(UINT64, UInt64, uint64_t, Json::UInt64);
#undef XX
      case google::protobuf::FieldDescriptor::CPPTYPE_ENUM: {
        value[field->name()] = reflection->GetEnum(message, field)->number();
        break;
      }
      case google::protobuf::FieldDescriptor::CPPTYPE_STRING: {
        value[field->name()] = reflection->GetString(message, field);
        break;
      }
      case google::protobuf::FieldDescriptor::CPPTYPE_MESSAGE: {
        serialize_message(reflection->GetMessage(message, field), value[field->name()]);
        break;
      }
    }
  }
}

int main()
{
    // std::string url[]={"flex_enb_config_reply","person_info","phone","number","string"};

  Json::Reader reader;
  Json::Value sta;
  std::ifstream in("sta.json", ios::binary);

  if(!in.is_open()){
    cout << "Error opening file\n";
    return 0;
  }

  if(reader.parse(in, sta)){
    std::cout<<sta.toStyledString()<<std::endl;
  }

  book::AddressBook ab;
  book::Person* pi = ab.add_person_info();

  pi->set_name("aut");
  pi->set_id(1087);
  if (!pi->has_email())
      pi->set_email("autyinjing@126.com");

  book::Person::PhoneNumber* pn = pi->add_phone();
  // pn->add_number("021-8888-8888");
  pn = pi->add_phone();
  // auto num=pn->add_number();
  // num->set_
  pn->set_number("138-8888-8888");
  pn->set_type(book::Person::MOBILE);
  auto add = pn->add_address(); 
  add->set_street("Jin Qiu Lu");
  add->set_house_number(3211);
  auto add2 = pn->add_address(); 
  // add2->set_street("Shang Da Lu");
  // add2->set_house_number(3210);
  std::cout<<ab.DebugString()<<std::endl;

  std::string keys[]={"person_info"};
  Json::Value father;
  std::string key="person_info";
  if(key==keys[0]){
    Json::Value child;
    fillJson(sta[key],child,key,*pi);
    father[key]=child;
  }

  std::cout<<father.toStyledString()<<std::endl;

  return 0;
}