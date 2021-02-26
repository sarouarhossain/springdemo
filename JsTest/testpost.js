const axios = require('axios');

axios.post("http://localhost:9090/api/v1/user",
    {
        username: 'Sakib',
        email: 'test@gmail.com',
        password: '1234',
        age:'53'
    }
).then(function(response){
    console.log(response.data)
}).catch(function(error){
    console.log(error.data)
})