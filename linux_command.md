## 리눅스 명령어

- ***chown/chmod***: 파일이나 디렉토리의 소유자, 그룹을 바꾸어 주는 명령어

```bash
chown [option]... [owner][:[group]] file or directory
chmod [option]... mode[,mode]... file or directory

# option
# -R: 하위 파일과 폴더들까지 적용하는 옵션
```

- ***mv/rename***: 파일이나 디렉토리의 이름을 변경하는 명령어

```bash
mv [option]... [-T] source dest

# mv는 파일이나 디렉토리 이동시에도 사용

# example
# 현재 디렉토리의 test1.txt를 상위 폴더로 test2.txt로 이름 변경하여 이동
# mv test1.txt ../test2.txt

rename [options] expression replacement file or directory

# '?', '*'과 같은 문자열 패턴을 사용할 수 있음

# example
# 파일 이름에 있는 test 문자열을 TEST 문자열로 변경
# test1.txt / test2.txt => TEST1.txt / TEST2.txt
# rename test TEST *.txt
```
