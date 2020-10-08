// 测试程序：
#include <iostream>
#include <string>
#include "jsoncpp/json/json.h"
#include "jsoncpp/json/value.h"
#include "jsoncpp/json/writer.h"
 
using namespace std;
 
int main()
{
    cout << "Hello World!" << endl;
 
    Json::Value root;
    Json::FastWriter fast;
 
    root["ModuleType"] = Json::Value("1");
    root["MOduleCode"] = Json::Value("China");
 
    cout<<fast.write(root)<<endl;
 
    Json::Value sub;
    sub["version"] = Json::Value("1.0");
    sub["city"] = Json::Value(root);
    fast.write(sub);

    cout<<sub.toStyledString()<<endl;

 	Json::Value::Members members = sub.getMemberNames();  
 	for (Json::Value::Members::iterator it = members.begin();  
  	it != members.end(); ++it) 
	{  
    	std::string name = *it;  
   		std::string value_str = sub[name].toStyledString();// .asString();  
    	// cout<<name<<":"<<value_str<<"type"<<sub[name].isMember()<<endl;  
  	} 
 
    return 0;
}
// 编译：g++ -o test test.cc -l jsoncpp
// 运行：./test