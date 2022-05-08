// get form
let form = document.forms['reset-password-form'];

// get all elements
let pass = form['new-password'];
let cnfPass = form['cnf-password'];
let button = form.querySelector('button');

// get login button, register button & error elements
let loginButton = document.querySelector('#login-link-button');
let registerButton = document.querySelector('#register-link-button');
let errorDiv = document.querySelector('.error-div');
let oldPassErrorDiv = document.querySelector('[name=old-pass-error]')

form.addEventListener('submit', (event) => {
	// prevent form submit
	event.preventDefault();
	
	// disable inputs, buttons and hide error elements
	errorDiv.style.display = 'none';
	oldPassErrorDiv.style.display = 'none';
	
	if(pass.value === cnfPass.value) {
		let postObj = {newPassword: pass.value}
		makePostRequest('validate-password-reset', postObj);
	}else {
		errorDiv.style.display = 'block';
	}
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

// if old and new passwords are differnet submit form or else display error
const postProcess = (responseObj) => {
	if(responseObj['password-reset'] === 'successful') {
		form.submit();
	}else {
		oldPassErrorDiv.style.display = 'block'
	}
}