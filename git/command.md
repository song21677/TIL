* stage -> unstage 
  * ``git reset HEAD [file]``
    * file : unstage할 file명
  * ``git reset HEAD``
    * stage에 올라간 파일 모두 add를 취소할 수 있다.

<br><br>

* push전 Staging Area에 들어간 commit 취소하기
  * ``git reset HEAD~1``
    * 바로 이전 commit이 취소되고, unstage상태로 돌아간다.
  * git reset --soft HEAD~1
    * 바로 이전 commit이 취소되고, stage 상태이다.

* 바로 이전 commit message 변경하기
  * ``git commit --amend``