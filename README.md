# System wind
## Opis programu
1) System pozwala na stworzenie od 1 do 16 wind
2) W każdej chwili możemy sprawdzić status naszego systemu, który pokazuje ID każdej z wind, piętro na którym aktualnie się znajduje oraz piętro docelowe
3) Możemy przywołać windę (pickup) - najbliższa winda, która nie jest zajęta, zmienia status na zajętą i zaczyna się kierować w stronę tego piętra
4) Do każdej windy, która nie jest aktualnie zajęta, możemy "wejść" i wybrać piętro docelowe, na które ma dojechać - zmienia wtedy też status na zajętą
5) System pozwala także na wykonanie kroku symulacji, czyli ruchu odpowiednimi windami

## Uruchomienie 
Po rozpakowaniu archiwum wystarczy wpisać:
```sh
  gradlew run
  ```
lub zaimportować projekt do IDE (np. IntelliJ) i skorzystać z opcji Gradle>Tasks>Application>Run:
![image](https://user-images.githubusercontent.com/72704714/162578272-27e38225-34e9-4ad3-b715-3aceec1b5ff4.png)

## Testy
W projekcie znajdują się również testy jednostkowe JUnit, które sprawdzają działanie dwóch głównych klas: Elevator oraz ElevatorSystem.

## UI
Jest to aplikacja okienkowa z wykorzystaniem JavaFX:
1) Na start należy wybrać liczbę wind (od 1 do 16):
![image](https://user-images.githubusercontent.com/72704714/162577623-2074bcd7-46d6-4bba-acec-af3bcab14a18.png)
2) Następnie pojawiają nam się panele dla każdej z wind:
![image](https://user-images.githubusercontent.com/72704714/162577660-8479800b-ac04-480c-9db1-a4f89612d2ee.png)
3) Możemy tutaj sprawdzić status wszystkich wind:
![image](https://user-images.githubusercontent.com/72704714/162577672-2c3115ba-9135-4e2d-ba36-0f5caa3a248c.png)
4) Przywołanie najbliższej, niezajętej windy na wybrane piętro (symulacja naciśnięcia przycisku przed windą):
![image](https://user-images.githubusercontent.com/72704714/162577728-6daad4c2-9566-4fc4-b9be-bb3130679514.png)
5) Wykonanie kroku symulacji (czyli ruchu windy):
![image](https://user-images.githubusercontent.com/72704714/162577809-3431e323-fa6a-4e3d-a05f-7c6198596e9e.png)
6) Korzystając z paneli wind, możemy wybrać piętro, na które ma wyjechać (symulacja naciśnięcia piętra w windzie):
![image](https://user-images.githubusercontent.com/72704714/162577863-fa9a4904-e661-44f9-8afe-5aef2748e30a.png)
7) Wykonanie kroków symulacji, aby każda winda znalazła się na docelowym piętrze:
![image](https://user-images.githubusercontent.com/72704714/162577971-e1c22514-db1e-4d90-b091-01b3af9fd3bc.png)


