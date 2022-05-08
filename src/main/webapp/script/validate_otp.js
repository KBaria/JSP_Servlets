// get form
let form = document.forms['otp-validation-form'];

// get form elements
let otp = form['password'];
let button = form.querySelector('button');

// get loader, error, login button & register button elements
let loaderDiv = document.querySelector('.loader-div');
let errorDiv = document.querySelector('.error-div');
let loginButton = document.querySelector('#login-link-button');
let registerButton = document.querySelector('#register-link-button');

form.addEventListener('submit', (event) => {
	// prevent form submit
	event.preventDefault();
	
	// disable buttons for a while hide loader and error elements
	otp.disabled = true;
	button.disabled = true;
	loginButton.disabled = true;
	registerButton.disabled = true;
	
	loaderDiv.style.display = 'block';
	errorDiv.style.display = 'none';
	
	let postObj = {
		otp: otp.value
	}
	
	makePostRequest('validate-otp', postObj);
});

// function for making post request
const makePostRequest = (url, postObj) => {
    fetch(url, {
        method: 'POST',
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

// aftermaking request enable buttons and hide loader div and if otp is correct submit form
// if otp is incorrect display error element
const postProcess = (responseObj) => {
	otp.disabled = false;
	button.disabled = false;
	loginButton.disabled = false;
	registerButton.disabled = false;
	loaderDiv.style.display = 'none';
	
	if(responseObj['otp-validation'] === 'successful') {
		form.submit();
	}else {
		errorDiv.style.display = 'block';
	}
}