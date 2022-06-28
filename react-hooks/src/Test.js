import React from "react";
import { useCountRenders } from "./useCountRenders";

export const Test = React.memo(({increment}) => {
    useCountRenders();

    return <button onClick={() => increment(5)}>Test</button>
})