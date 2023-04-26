# 비트 연산자
* 종류
  * & : 비트단위로 AND 연산
  * | : 비트단위로 OR 연산
  * ^ : 비트단위로 XOR 연산(같으면 0, 다르면 1)
  * ~ : 단항 연산자, 피연산자의 모든 비트를 반전
  * << : 피연산자의 비트 열을 왼쪽으로 이동
  * \>> : 피연산자의 비트 열을 오른쪽으로 이동
* 다른 연산자들에 비해 실행 시간이 적게 소요됨
* 연산 속도 향상, 메모리 절약 가능
* 예시
  * 변수에 저장된 양의 정수 값이 홀수 혹은 짝수인지 판별
    * N % 2
    * **N & 1**
  * 부분집합의 수 계산
    * **1 << n** : 2^n
  * i의 특정 위치(j) 비트 값이 1인지 0인지 판별
    * **i & (1 << j)** : 1이면 비트 값은 1, 0이면 비트 값은 0
    * 예제
      * 8비트의 문자형 변수에 저장된 비트 값들을 출력하는 함수
        ```c
        void main(void)
        {
            char i;

            void BitPrint(char i)
            {
                for (int j=7; j>=0; j--)
                    putchar((i & (1 << j) ? '1' : '0'));
                    // printf("%d", (i >> j) & 1);
            }
            for (i=-5; i<6; i++) {
                printf("%3d = ", i);    // 십진수 출력
                BitPrint(i);            // 이진수 출력
                putchar('\n');
            }
        }
        ```
      * 4바이트 크기의 인티저형 변수에 저장된 값들을 한 바이트씩 읽어서 비트형태로 출력
        ```java
        void main(void)
        {
            char *p;
            char a = 0x10;  // 16진수 표현
            int x = 0x01020304, i;
            printf("%d = ", a);
            p = &a;
            Bit_print(*p);
            putchar('\n');

            printf("0%X = ", x);
            p = (char *) &x;
            for (i=0; i<4; i++)
                Bit_print(*p++);
                putchar('\n');

        }
        ```
    * 엔디안
      * 엔디안 확인 코드
        ```java
        main(void)
        {
            int n = 0x00111111;
            char *c = (char *)&n;

            if(c[0])    // 11
                printf("little endian");
            else
                printf("big endian");
        }
        ```
      * 엔디안 변환 코드
        ```java
        void ce(int * n)    // change endian
        {
            char *p = (char *) n;
            char t;
            t = p[0], p[0] = p[3], p[3] = t;
            t = p[1], p[1] = p[2], p[2] = t;
        }
        void ce1(int *n)
        {
            *n = (*n<<24) | (*n<<8 & 0xff0000) |(*n>>8) & 0xff00 | (*n>>24); 
        }
        main()
        {
            int x = 0x01020304;
            char *p = (char *) &x;

            printf("x = %d%d%d%d\n", p[0], p[1], p[2], p[3]);
            ce(&x);
            printf("x = %d%d%d%d\n", p[0], p[1], p[2], p[3]);
        }
        ```   
    * Byte Alignment
      * 32bit 장치의 경우
        * 주소버스가 4의 배수형태의 주소만 access
        * 어떤 객체(4byte)가 4의 배수형 주소에 있지 않다면 메모리 access를 2번 해야 함
      * 변수 별 저장 가능한 주소의 번지 패턴
        * 1byte: 모든 주소 번지에 기록 가능
        * 2byte: 2byte boundary에 정렬
        * 4byte: 4byte boundary에 정렬
        * Double(8byte): windows에서는 8byte, 리눅스에서는 4byte boundary
      * Struct Bute Padding
        * 구조체의 멤버들 사이에 임의의 공간이 생기는 현상
        * 구조체의 경우 멤버 중 가장 큰 데이터 타입의 배수 값으로 크기가 결정
        * 구조체 멤버들의 순서를 잘 배치하면 필요한 메모리의 크기를 줄일 수 있다.
            ![캡처](https://user-images.githubusercontent.com/55786368/234169384-bf89067e-4b61-4030-9938-81b379c74d6e.PNG)
            ```c
            // int 배수 값(12)로 크기 결정
            struct Message
            {
                char Data1;
                short Data2;
                int Data3;
                char Data4;
            }m;

            /*
            메모리 크기 줄이기, 줄인 결과 메모리 크기는 8
            struct Message
            {
                char Data1;
                char Data4;
                short Data2;
                int Data3;
            }
            */
            void main(void)
            {
                printf("%p\n", &m.Data1);
                printf("%p\n", &m.Data2);
                printf("%p\n", &m.Data3);
                printf("%p\n", &m.Data4);

                printf("%dn", sizeof(struct Message));
            }
            ```

<br><br>

### Reference
* [swea - 비트연산](https://swexpertacademy.com/main/learn/course/subjectDetail.do?courseId=AVuPDYSqAAbw5UW6&subjectId=AV2dX7FaAcsBBASw&&)