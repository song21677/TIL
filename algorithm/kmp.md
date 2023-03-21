# KMP 알고리즘
* 시간 복잡도 : O(m + n)
* 불일치가 발생한 텍스트 스트링의 앞 부분에 어떤 문자가 있는지를 미리 알고 있으므로, 불일치가 발생한 앞 부분에 대하여 다시 비교하지 않고 매칭을 수행한다.
* next[m] : 불일치가 발생했을 경우 이동할 다음 위치
* 텍스트에서 abcdabc까지는 매치되고, e에서 실패한 상황에서, 패턴의 맨 앞의 abc와 실패 직전의 abc는 동일함을 이용할 수 있다.
* 실패한 텍스트 문자와 P[4]를 비교
  ![{BDF50C88-85EE-4E7A-9BAA-E0E2B55FF5DF}](https://user-images.githubusercontent.com/55786368/226412076-0cfd0d92-d729-49b2-a5e1-e0aa7227e766.png)
* 매칭이 실패했을 때 돌아갈 곳을 계산
  ![{90C543F7-2DEB-499A-8DDF-FC46749EB9E3}](https://user-images.githubusercontent.com/55786368/226412654-54d85b82-c926-47e3-8eb7-81a39f230297.png)
* 코드
  ```java
  static void KMP(String parent, String pattern) {
    int[] table = makeTable(pattern);
		
	int n1 = parent.length();
	int n2 = pattern.length();
		
	int idx = 0; // 현재 대응되는 글자 수
	for(int i=0; i< n1; i++) {
		while(idx>0 && parent.charAt(i) != pattern.charAt(idx)) {
			idx = table[idx-1];
		}	
		if(parent.charAt(i) == pattern.charAt(idx)) {
			if(idx == n2-1) {
				System.out.println(i-idx+1 + "번째에서 찾았습니다. ~" + (i+1) );
				idx =table[idx];
			}else {
				idx += 1;
			}
		}
    }
  }

  static int[] makeTable(String pattern) {
	int n = pattern.length();
	int[] table = new int[n];
		
	int idx=0;
	for(int i=1; i<n; i++) {
		while(idx>0 && pattern.charAt(i) != pattern.charAt(idx)) {
			idx = table[idx-1];
		}
			
		if(pattern.charAt(i) == pattern.charAt(idx)) {
			idx += 1;
			table[i] = idx;  
		}
	}
	return table;
  }

  ```
<br><br>

  ## Reference
  * [SWEA - String 2차시 패턴 매칭] (https://swexpertacademy.com/main/learn/course/lectureVideoPlayer.do#none)
  * https://loosie.tistory.com/192
