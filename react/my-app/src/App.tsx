import { useState } from "react";

function App() {
  const [count, setCount] = useState(0);
  const [user, setUser] = useState({
    id: "12",
    name: "Nghĩa",
    address: "117 Tran Hung Dao - Quan 1",
  });
  const [animals, setAnimals] = useState(["Dog"]);

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

  return (
    <div>
      <button onClick={() => setCount(count + 1)}>Count: {count}</button>
      <p>Animals: {animals.join(", ")}</p>
      <button onClick={addAnimal}>Add Animal</button>
      <p>
        User: {user.name} - {user.address}
      </p>
      <button onClick={changeName}>Change Name</button>
    </div>
  );
}

export default App;
