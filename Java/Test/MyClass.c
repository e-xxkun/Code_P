#include <stdio.h>

struct a {
    char a;
    short b;
    void *c;
};
struct b{ 
    char a;
    short b;
    void *c;
}__attribute__((packed));

 int main(void){
     printf("%d, %d\n", sizeof(struct a), sizeof(struct b));
     return 0;
 }
