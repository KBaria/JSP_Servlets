// get the registration form
let form = document.getElementById('registration-form');

// get all input elements
let firstName = form['first-name'];
let lastName = form['last-name'];
let email = form['email'];
let pass = form['password'];
let cnfPass = form['cnf-password'];
let contact = form['contact'];

// on form submit call this function
form.addEventListener('submit', (event) => {
	// prevent form submit
    event.preventDefault();
    
	// get the password error element
    let passError = document.querySelector('[name=password-error]');

	// if passwords are equal send request to Validate Registration servlet
    if(pass.value === cnfPass.value) {
        
        // set display property of password error element
        passError.style.display = 'none';

		// set up post request body
        let postBody = {
			email: email.value,
			firstName: firstName.value,
            lastName: lastName.value,
            password: pass.value,
            contact: contact.value
        }
		
		// make request
        makeRequest('validate-registration', 'POST', postBody);
    }else {
		// if passwords are unequal set display of password error element to block
		console.log('password mismatch');
        passError.style.display = 'block';
	}
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
// if registration is successful submit form to Home servlet
const postProcess = (responseObj) => {
    let emailError = document.querySelector('[name=email-error]');
    emailError.style.display = responseObj['email-validity'];
    
    let contactError = document.querySelector('[name=contact-error]');
    contactError.style.display = responseObj['contact-validity'];
    
    if(responseObj['user-registration'] === 'successful'){
		form.submit();
	}
}