const axios = require('axios');

axios.get("http://localhost:9090/api/v1/user").then(function (response) {
    // handle success
    console.log(response.data);
  }).catch(function (error) {
    // handle error
    console.log(error.data);
  })