////
//// Created by KC-14 on 2018/12/15.
////
//#define PI 3.14
//
//class MyCircle {
//private:
//    double r;
//    double s;
//public:
//    void setR(double r) {
//        this->r = r;
//    }
//
//    double getS() {
//        return PI * r * r;
//    }
//};
//
//void main() {
//    MyCircle myCircle;
//    myCircle.setR(2);
//    std::cout << "圆的面积" << myCircle.getS() << std::endl;
//    system("pause");
//}
#include <iostream>
using namespace std;
struct MyTeacher{
public:
    char name[20];
    int age;
public:
    void say(){
        cout<<this->age<<"岁"<<endl;
    }
};
void main(){
    MyTeacher myTeacher;
    myTeacher.age=12;
    myTeacher.say();
    system("pause");
}
