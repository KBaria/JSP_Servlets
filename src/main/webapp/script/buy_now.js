// currency formatter
let formatter = new Intl.NumberFormat('en-IN', { style: 'currency', currency: 'INR' });

// get all cart list items
let cartItem = document.querySelectorAll('.cart-list-item');

// update cart total 
const updateCartTotal = () => {
	let cartItems = document.querySelectorAll('.cart-list-item');
	let sum = 0;
	for(const item of cartItems) {
		let unitPrice = item.querySelector('.product-unit-price');
		let unitPriceValue = parseFloat(unitPrice.getAttribute('data-price'));
		
		let qty = parseInt(item.querySelector('.product-qty').textContent);
		sum += qty * unitPriceValue;
	}
	let cartTotal = document.querySelector('.cart-total-value');
	cartTotal.textContent = formatter.format(sum);
}

updateCartTotal();

// get all product data elements
let products = document.querySelectorAll('.cart-item-data');

// get sub total container
let cartSubtotal = document.querySelectorAll('.cart-subtotal-container');

// format unit price data
for(const product of products){
	let price = product.querySelector('.product-unit-price');
	price.textContent = formatter.format(price.getAttribute('data-price'));
}

// format cart subtotal price data
for(const subTotal of cartSubtotal){
	let price = subTotal.querySelector('.item-subtotal');
	price.textContent = formatter.format(price.getAttribute('data-price'));
}

// get order list
let productList = document.querySelector('.product-list');

// update qty
productList.addEventListener('click', (event) => {
	let listItem;
	if(event.target.className === 'increment-button') {
		listItem = event.target.parentElement.parentElement;
		let productQty = getQty(listItem);
		productQty.textContent++;
		updatePrice(listItem, productQty.textContent);
	}else if(event.target.className === 'decrement-button'){
		listItem = event.target.parentElement.parentElement;
		let productQty = getQty(listItem);
		if(productQty.textContent == 1) {
			if(confirm('Cannot decrement quantity further\nWant to cancel order?') == true){
				document.getElementById('home-link').click();
			}
		}else {
			productQty.textContent--;
			updatePrice(listItem, productQty.textContent);
		}
	}
});

// get quantity
const getQty = (listItem) => {
	let productQty = listItem.querySelector('.product-qty');
	return productQty;
}

// update subtotal price
const updatePrice = (listItem, qty) => {
	let unitPrice = listItem.querySelector('.product-unit-price').getAttribute('data-price');
	let subTotal = listItem.querySelector('.item-subtotal');
	subTotal.textContent = formatter.format(unitPrice * qty);
	updateCartTotal();
}

// get checkout form
let form = document.forms['checkout-form'];

// 
form.addEventListener('submit', (event) => {
	// prevent form submit
	event.preventDefault();

	// create hidden input elements and append them to the form
	let productId = createElement('id', document.querySelector('.cart-item-data').getAttribute('data-productId'));
	let qty = createElement('qty', document.querySelector('.product-qty').textContent);
	
	form.appendChild(productId);
	form.appendChild(qty);
	
	// submit form
	form.submit();
});

// create hiden input elements
const createElement = (name, value) => {
	let input = document.createElement('input');
	input.setAttribute('name', name);
	input.setAttribute('value', value);
	input.setAttribute('type', 'hidden');
	return input;
}

// create order session
const createOrder = () => {
	fetch('create-order-session', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json'
        }
    })
    .then(response => response.json())
    .then(data => {
        console.log('Success:', data);
        if(data['order'] === 'successful'){
			form.submit();
		}else {
			alert('something went wrong');
		}
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}