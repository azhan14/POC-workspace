import { useCallback, useEffect, useLayoutEffect, useRef, useState } from 'react';
import './App.css';
import { Hello } from './Hello';
import { Square } from './Square';
import { Test } from './Test';
import { useFetch } from './useFetch';
import { useForm } from './useForm';

function App() {

  const [count, setCount] = useState(0);

  const [{count1, count2}, setCount2] = useState({count1 : 10, count2 : 20});

  // const[email, setEmail] = useState("");
  // const[password, setPassword] = useState("");

  const [values, handleChange] = useForm({email: "", password: ""});

  // useEffect(() => {
  //   // console.log("render");
  //   const onMouseMove = e => {
  //     console.log(e);
  //   }
  //   window.addEventListener('mousemove', onMouseMove);

  //   return () =>{
  //     window.removeEventListener("mousemove", onMouseMove)
  //   }
  // } ,[]);


  // Multiple Use Effect
  // useEffect(() => {
  //   console.log('event1');
  // })

  // useEffect(() => {
  //   console.log('event2')
  // })

  // https://numbersapi.com/43/trivia
  // const [counter, setCounter] = useState(() => 
  // JSON.parse(localStorage.getItem("counter")));
  
  // const{data, loading} = useFetch(`http://numbersapi.com/${counter}/trivia`);
  
  // useEffect(() =>{
  //   localStorage.setItem("counter", JSON.stringify(counter));
  // }, [counter]);

  const [showHello, setShowHello] = useState(true);

  const inputRef = useRef();


  // useLayoutEffect(() =>{
  //   console.log(inputRef.current.getBoundingClientRect())
  // }, [])


  const increment1 = useCallback(
    n => {
      setCount( c => c + n);
    }, [setCount]
  );

  const favNumArr = [7, 10, 30];

  const {data} = useFetch("https://raw.githubusercontent.com/ajzbc/kanye.rest/master/quotes.json");

  const computeLongestWord = (arr) => {
    if(!arr) {
      return [];
    }

    console.log('computing longest word.')
    let longestWord = "";

    JSON.parse(arr).forEach(sentence => 
      sentence.split(" ").forEach(word => {
        if(word.length > longestWord.length) {
          longestWord = word;
        }
      }))

      return longestWord;
  }

  return (
    <div className="App">
        <button onClick={() => setCount(currentCount => currentCount + 1)}>
          Increment
        </button>
        <div>{count}</div>
        <hr></hr>
        {/* add ...currentState if you want to keep count2 in the set. or else it will disappear */}
        <button onClick={() => setCount2(currentState => ({...currentState, count1: currentState.count1 + 1 }))}>
          Increment
        </button>
        <div>count 1 : {count1}</div>
        <div>count 2 : {count2}</div>

        <hr></hr>
        <input 
          ref={inputRef}
          name='email' 
          value={values.email}
          onChange={handleChange}
          />
        <input 
          type="password" 
          name='password' 
          value={values.password}
          onChange={handleChange}
          />

          <button onClick={() => console.log(inputRef.current)}>ref</button>
          <button onClick={() => inputRef.current.focus()}>focus</button>

          <div>{values.email} {values.password}</div>
          <hr>
          </hr>

          <button onClick={() => setShowHello(!showHello)}>Toggle</button>
          {showHello && <Hello />}

          {/* <hr/> */}
          {/* <div>count : {counter}</div>
          <button onClick={() => setCounter(c => c+ 1)}>Increment</button>
          <div>{!data ? "loading..." : data}</div> */}

          <hr/>

          <div>
            <Test increment = {increment1} />
            <div>count : {count}</div>

            {favNumArr.map(n => {
              return(
                <Square increment={increment1} n = {n} key={n}/>
              )
            })}
          </div>
          <hr/>
          <div>{computeLongestWord(data)}</div>
    </div>
  );
}

export default App;
