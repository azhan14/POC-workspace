import React, { useEffect, useState } from 'react';
import { makeStyles } from '@material-ui/core/styles';
import TextField from '@material-ui/core/TextField';
import { Container, Paper, Button} from '@material-ui/core';

const useStyles = makeStyles((theme) => ({
  root: {
    '& > *': {
      margin: theme.spacing(1),
      width: '25ch',
    },
  },
}));

export default function Student() {
    const paperStyle = {padding: '50px 20px', width:600, margin:'20px auto'};
  const[name, setName] = useState('');
  const[address, setAddress] = useState('');
  const[students, setStudents] = useState([]);
  const classes = useStyles();

  const handleClick = (e) => {
    e.preventDefault();
    const student = {name, address};
    console.log(student);

    fetch("http://localhost:8080/student/add",{
        method:"POST",
        headers:{"Content-Type": "application/json"},
        body: JSON.stringify(student)
    }).then(() => {
        console.log("new student added")
    });
  }

  useEffect(() => {
    fetch("http://localhost:8080/student/getAll")
    .then(res => res.json())
    .then((result) => {
        setStudents(result);
    })
  },[]);
  

  return (
    <Container>
        <Paper elevation={3} style={paperStyle}>
        <h1 style={{color:'blue'}}>Add Student</h1>
        <form className={classes.root} noValidate autoComplete="off">
            <TextField id="outlined-basic" label="Name" variant="outlined" 
                value={name} onChange={(e) => setName(e.target.value)}
                fullWidth
            />
            <TextField id="outlined-basic" label="Address" variant="outlined"
                value={address} onChange={(e) => setAddress(e.target.value)}
                fullWidth
            />
            <Button variant="contained" color="primary" onClick={handleClick}>
                Submit
            </Button>
        </form>
        </Paper>

        <Paper elevation={3} style={paperStyle}>
            {students.map(student => {
                return <Paper elevation={6} style={{margin:"10px", padding:"15px", textAlign:"left"}} 
                key={student.id}>
                    Id: {student.id}<br/>
                    Name: {student.name}<br/>
                    Address: {student.address}
                </Paper>
            })}

        </Paper>
    </Container>
  );
}
