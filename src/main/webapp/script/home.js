// currency formatter
let formatter = new Intl.NumberFormat('en-IN', { style: 'currency', currency: 'INR' });

// get all product data elements
let products = document.querySelectorAll('.product-data');

// function to format product price
const formatPrice = (product) => {
	let price = product.querySelector('.product-price');
	price.textContent = formatter.format(price.getAttribute('data-price'));
}

// format price for each product
for(const product of products) {
	formatPrice(product);
}

let productList = document.querySelector('.product-list');

// catch click on every products in the list
productList.addEventListener('click', (event) => {
	if(event.target.className === 'add-to-cart-button') {
		let listItem = event.target.parentElement.parentElement.parentElement;
		let productId = listItem.querySelector('.product-data').getAttribute('data-productId');
		addProductToCart(productId);
	}
});

// make request to AddToCartServlet
const addProductToCart = (productId) => {
	let postObj = {id: productId};
	
	fetch('add-to-cart', {
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

// get response object and raise appropriate alerts
const postProcess = (data) => {
	if(data['cart-item'] === 'added-new'){
	alert('Product added to your cart');
	}else if(data['cart-item'] === 'incremented'){
		alert('Product exists in cart, quantity updated');
	}else if(data['user-session'] === 'invalid'){
		if(confirm('You must be logged in to use this feature\nWant to login?') == true){
			document.getElementById('login-link').click();
		}
	}
}

// check for user session
// const checkUserSession = () => {
// 	fetch('validate-user', {
// 		method: 'GET'
// 	})
// 	.then(response => response.json())
//     .then(data => {
//         console.log('Success:', data);
//         if(data['user-session'] === 'invalid'){
// 			if(confirm('You must be logged in to use this feature\nWant to login?') == true){
// 				document.getElementById('login-link').click();
// 			}
// 		}
//     })
//     .catch((error) => {
//         console.error('Error:', error);
//     });
// }
