// get login form
let form = document.getElementById('login-form');

// get all input elements
let email = form['email'];
let password = form['password'];

// on form submit call function
form.addEventListener('submit', (event) => {
	// prevent form submit
	event.preventDefault();
	
	// set up post request body
	let postObj = {
		email: email.value,
		password: password.value
	}
	
	// make request
	makeRequest('validate-login', 'POST', postObj);
});

// function for making request
const makeRequest = (url, method, postObj) => {
    fetch(url, {
        method: method,
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(postObj)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        postProcess(data);
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

// after making request and recieving response set up display properties for error elements 
// if login is successful submit form to Home servlet
const postProcess = (responseObj) => {
	if(responseObj['user-login'] === 'successful'){
		form.submit();
	}else if(responseObj['user-login'] === 'unsuccessful'){
		let errorDiv = document.querySelector('[name=error]');
		errorDiv.style.display = 'block';
	}
}