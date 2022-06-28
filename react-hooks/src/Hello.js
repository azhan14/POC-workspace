import React, { useEffect, useLayoutEffect, useRef, useState} from "react";
import { useFetch } from "./useFetch";
import { useMeasure } from "./useMeasure";

export const Hello = () => {
    const renders = useRef(0);
    const [counter, setCounter] = useState(() => 
  JSON.parse(localStorage.getItem("counter")));
  
  const{data, loading} = useFetch(`http://numbersapi.com/${counter}/trivia`);
  
  useEffect(() =>{
    localStorage.setItem("counter", JSON.stringify(counter));
  }, [counter]);

  const [rect, divRef] = useMeasure([data]);

//   const [rect, setRect] = useState({});

//   useLayoutEffect(() => {
//     setRect(divRef.current.getBoundingClientRect())
//   }, [data])


    return (
    <div>
        <div>count : {counter}</div>
        <button onClick={() => setCounter(c => c+ 1)}>Increment</button>
        <div style={{display: 'flex'}}>
            <div ref={divRef}>{!data ? "loading..." : data}</div>
        </div>
        <pre>{JSON.stringify(rect, null, 2)}</pre>
    </div>
    );
    
}