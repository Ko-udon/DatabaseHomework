# DatabaseHomework

![image](https://user-images.githubusercontent.com/79897135/202923335-a8c6b9e1-e21f-419e-9aa8-916ebcaa36ba.png)
![image](https://user-images.githubusercontent.com/79897135/202923349-c68fc857-5296-4101-80a7-0f9cee2b33f3.png)
![image](https://user-images.githubusercontent.com/79897135/202923356-29ff83e5-f67b-4d91-bfef-f7c0a89c51e0.png)
![image](https://user-images.githubusercontent.com/79897135/202923358-61be38c9-7f59-4252-9928-8827546a36a0.png)
![image](https://user-images.githubusercontent.com/79897135/202923360-b31bc399-0e61-4de1-a168-b0321e9db1d0.png)
![image](https://user-images.githubusercontent.com/79897135/202923362-9ad620af-561a-40a2-a503-d4f63439b3eb.png)


=======================================================================================================================================
1.	프로그램 실행 화면
 ![image](https://user-images.githubusercontent.com/79897135/202923423-d6506ba9-eb0d-4624-bdef-111c93021bdd.png)


2.	(1) 실행
![image](https://user-images.githubusercontent.com/79897135/202923429-3a505b9e-70f3-48c6-acf3-4b71b79933e4.png)

 
3.	(2) 제목 키워드 검색: 3D’
	String sql="select * from movie where title LIKE ? order by id asc";
![image](https://user-images.githubusercontent.com/79897135/202923445-dce8085e-84a6-4b6d-abc7-d55db9b7e975.png)

 



4.	(3) 관객수를 이용한 검색 : 1,000,000만 관객 이상 영화
	String sql="select * from movie where totalnum > ? order by id asc";
![image](https://user-images.githubusercontent.com/79897135/202923454-9b412ceb-52aa-4d58-9b51-beeb0f3a82e6.png)

 

7.	(4) 개봉일을 이용한 검색 :  2013-02-20 ~ 2013-08-31 사이 개봉한 영화
	String sql="select * from movie where releasedate between ? and ? order by id asc"
![image](https://user-images.githubusercontent.com/79897135/202923463-56fe6608-6047-41b6-8531-3ffb1ee1c31a.png)



2013년 2월 사이에 개봉한 영화
 ![image](https://user-images.githubusercontent.com/79897135/202923472-8be6db84-9d92-4445-99ef-b7605d8d2e40.png)


9.	(1) 다시 실행
 ![image](https://user-images.githubusercontent.com/79897135/202923475-6b552517-b3fe-4333-9d76-f401c32bf712.png)



10.	(1) 릴레이션 생성하기 전 다른 기능 입력 
![image](https://user-images.githubusercontent.com/79897135/202923477-524b0fc7-495c-4f63-94e9-eb9d11a9b918.png)

 

11.	(0) 프로그램 종료
![image](https://user-images.githubusercontent.com/79897135/202923482-da0959f2-80ad-469f-be54-4a4b20ee4d53.png)

 


