import { useState, useEffect } from "react";

function App() {
  const [count, setCount] = useState(0);
  const [user, setUser] = useState({
    id: "12",
    name: "Nghĩa",
    address: "117 Tran Hung Dao - Quan 1",
  });
  const [animals, setAnimals] = useState(["Dog"]);
  const [todo, setTodo] = useState<any>(null);

  const addAnimal = () => {
    const list = ["Cat", "Lion", "Tiger"];
    const random = list[Math.floor(Math.random() * list.length)];
    setAnimals([...animals, random]);
  };

  const changeName = () => {
    setUser({
      ...user,
      name: "Nghĩa Dương",
    });
  };

  useEffect(() => {
    fetch("https://jsonplaceholder.typicode.com/todos/7")
      .then((response) => response.json())
      .then((json) => {
        console.log(json);
        setTodo(json);
      });
  }, []);

  return (
    <div>
      <button onClick={() => setCount(count + 1)}>Count: {count}</button>
      <p>Animals: {animals.join(", ")}</p>
      <button onClick={addAnimal}>Add Animal</button>
      <p>
        User: {user.name} - {user.address}
      </p>
      <button onClick={changeName}>Change Name</button>

      <div
        style={{
          marginTop: "20px",
          borderTop: "1px solid #ccc",
          paddingTop: "10px",
        }}
      >
        <h3>API Todo:</h3>
        {todo ? (
          <p>
            Title: {todo.title} (ID: {todo.id})
          </p>
        ) : (
          <p>Đang tải dữ liệu...</p>
        )}
      </div>
    </div>
  );
}

export default App;
