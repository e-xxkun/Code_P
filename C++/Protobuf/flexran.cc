#include <iostream>
#include "book.pb.h"
#include "flexran/flexran.pb.h"

int main(int argc, const char* argv[])
{
    std::string url[]={"flex_enb_config_reply","cell_config","ul_bandwidth","uint32"};
    
    protocol::flex_enb_config_reply enb_config; 



    // pi->set_name("aut");
    // pi->set_id(1219);
    // std::cout << "before clear(), id = " << pi->id() << std::endl;
    // pi->clear_id();
    // std::cout << "after  clear(), id = " << pi->id() << std::endl;
    // pi->set_id(1087);
    // if (!pi->has_email())
    //     pi->set_email("autyinjing@126.com");

    // book::Person::PhoneNumber* pn = pi->add_phone();
    // pn->add_number("021-8888-8888");
    // pn = pi->add_phone();
    // // auto num=pn->add_number();
    // // num->set_
    // // pn->set_number(0,"138-8888-8888");
    // pn->set_type(book::Person::MOBILE);

    // uint32_t size = person.ByteSizeLong();
    // unsigned char byteArray[size];
    // person.SerializeToArray(byteArray, size);

    // book::AddressBook help_person;
    // help_person.ParseFromArray(byteArray, size);
    // book::Person help_pi = help_person.person_info(0);

    // std::cout << "*****************************" << std::endl;
    // std::cout << "id:    " << help_pi.id() << std::endl;
    // std::cout << "name:  " << help_pi.name() << std::endl;
    // std::cout << "email: " << help_pi.email() << std::endl;

    // // for (int i = 0; i < help_pi.phone_size(); ++i)
    // // {
    // //     auto help_pn = help_pi.mutable_phone(i);
    // //     std::cout << "phone_type: " << help_pn->type() << std::endl;
    // //     std::cout << "phone_number: " << help_pn->number() << std::endl;
    // // }
    // // std::cout << "*****************************" << std::endl;

    // std::cout<<help_person.DebugString()<<std::endl;
    // std::cout<<"************"<<std::endl;
    // auto p = help_pi.mutable_phone(1);
    // const google::protobuf::Reflection* reflection = p->GetReflection();
    // const google::protobuf::FieldDescriptor* field =p->GetDescriptor()->FindFieldByName("number");
    // std::cout<<"Value "<<(field->is_map())<<std::endl;
    // std::cout<<"Value "<< reflection->FieldSize(p[0], field)<<std::endl;

    

    return 0;
}