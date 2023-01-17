## Arrays 클래스

- 자바 API 클래스
- java.util 패키지에 있다.
    - java.lang 패키지 다음으로 가장 많이 사용하는 패키지이다.
    - import문으로 패키지를 불러오고 나서야 클래스 이름만으로 사용할 수 있다.
- Arrays 클래스의 모든 메소드는 클래스 메소드이다.
<br><br>

### 클래스 메소드(static method)

- 객체를 생성하지 않고도 바로 사용할 수 있다.
<br><br>

### 메소드

- binarySearch()
    - 전달받은 배열에서 특정 객체의 위치를 이진 검색 알고리즘을 사용하여 검색한 후, 해당 위치를 반환한다.
    - 매개변수로 전달되는 배열이 sort() 메소드 등을 사용하여 미리 정렬되어 있어야만 제대로 동작한다.

    ```java
    int[] arr = new int[1000];

    for(int i=0; i<arr.length; i++) {
        arr[i] = i;
    }

    System.out.println(Arrays.binarySearch(arr, 437));
    ```
<br>


- copyOf()
    - 전달받은 배열의 특정 길이만큼을 새로운 배열로 복사하여 반환한다.
    - 첫 번째 매개변수 : 원본 배열
    - 두 번째 매개변수 : 새로운 배열로 복사할 요소의 개수
    - 반환 값 : 원본 배열과 같은 타입의 복사된 새로운  배열
    - 새로운 배열의 길이가 원본 배열보다 길면, 나머지 요소는 배열 요소의 타입에 맞게 다음과 같은 기본값으로 채워진다.
        
        
        | 배열 요소의 타입 | 기본값 |
        | --- | --- |
        | char | '\u0000' |
        | byte, short, int | 0 |
        | long | 0L |
        | float | 0.0F |
        | double | 0.0 또는 0.0D |
        | boolean | false |
        | 배열, 인스턴스 등 | null |
    
    ```java
    int[] arr1 = {1, 2, 3, 4, 5};
    int[] arr2 = Arrays.copyOf(arr1, 4);
    
    for (int i = 0; i < arr2.length; i++) {
    		// 실행 결과
    		// 1 2 3
        System.out.print(arr2[i] + " ");
    }
    
    int arr3 = Arrays.copyOf(arr1, 10);
    for (int i = 0; i < arr3.length; i++) {
    		// 실행 결과
    		// 1 2 3 4 5 0 0 0 0 0
        System.out.print(arr3[i] + " ");
    }
    ```
<br>

- copyOfRange()
    - 전달받은 배열의 특정 범위에 해당하는 요소만을 새로운 배열로 복사하여 반환한다.
    - 첫 번째 매개변수 : 원본 배열
    - 두 번째 매개변수 : 원본 배열에서 복사할 시작 인덱스
    - 세 번째 매개변수 : 마지막으로 복사될 배열 요소의 바로 다음 인덱스
    - 반환 값 : 원본 배열과 같은 타입의 복사된 새로운 배열

    ```java
    int[] arr1 = {1, 2, 3, 4, 5};

    int[] arr2 = Arrays.copyOfRange(arr1, 2, 4);
    for (int i = 0; i < arr2.length; i++) {
            // 실행 결과
            // 3 4
        System.out.print(arr2[i] + " ");
    }
    ```
<br>

- fill()
    - 전달받은 배열의 모든 요소를 특정 값으로 초기화해준다.
    - 첫 번째 매개변수 : 초기화할 배열
    - 두 번째 매개변수 : 초기값

    ```java
    int[] arr = new int[10];

    Arrays.fill(arr, 7);
    for (int i = 0; i < arr.length; i++) {
            // 실행 결과
            // 7 7 7 7 7 7 7 7 7 7
        System.out.print(arr[i] + " ");
    }
    ```
<br>

- sort()
    - 전달받은 배열의 모든 요소를 오름차순으로 정렬한다.
    - 매개변수 : 정렬할 배열

    ```java
    int[] arr = {5, 3, 4, 1, 2};

    Arrays.sort(arr);
    for (int i = 0; i < arr.length; i++) {
            // 실행 결과
            // 1 2 3 4 5
        System.out.print(arr[i] + " ");
    }
    ```