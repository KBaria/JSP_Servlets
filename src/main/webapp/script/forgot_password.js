// get form
let form = document.querySelector('[name=forgot-password-form]');

// get form elements
let email = form['email'];
let button = form.querySelector('button');

// get loader, login button, register button and error elements
let loaderDiv = document.querySelector('.loader-div');
let loginButton = document.querySelector('#login-link-button');
let registerButton = document.querySelector('#register-link-button');
let errorDiv = document.querySelector('[name=error]');

form.addEventListener('submit', (event) => {
	// prevent form submit
	event.preventDefault();
	
	// disable buttons for a while hide loader and error elements
	email.disabled = true;
	button.disabled = true;
	loginButton.disabled = true;
	registerButton.disabled = true;
	
	loaderDiv.style.display = 'none';
	errorDiv.style.display = 'none';
	
	makeEmailValidationReq();
});

const makeEmailValidationReq = () => {
	// set up post request body
	let postObj = {
		email: email.value
	}
	
	// make request to servlet and validate email
	fetch('validate-email', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(postObj)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        
        if(data['email-validation'] === 'successful'){
			loaderDiv.style.display = 'block';
			makeOtpGenerationReq();
		}else {
			errorDiv.style.display = 'block';
			email.disabled = false;
			button.disabled = false;
			loginButton.disabled = false;
			registerButton.disabled = false;
		}
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

const makeOtpGenerationReq = () => {
	// set up post request body
	let postObj = {
		email: email.value
	}
	
	// make request to servlet and generation request
	fetch('generate-otp', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(postObj)
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        if(data['email-dispatch'] === 'successful') {
			form.submit();
		}else {
			errorDiv.textContent = 'Something went wrong!';
			errorDiv.style.display = 'block';
		}
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}