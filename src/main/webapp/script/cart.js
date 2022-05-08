// currency formatter
let formatter = new Intl.NumberFormat('en-IN', { style: 'currency', currency: 'INR' });

// get all cart list items
let cartItems = document.querySelectorAll('.cart-list-item');

// check if cart is empty or not
const checkCart = () => {
	let cartItems = document.querySelectorAll('.cart-list-item');
	
	if(cartItems.length == 0){
		document.querySelector('.empty-cart').style.display = 'flex';
		document.querySelector('.non-empty-cart').style.display = 'none';
	}else {
		document.querySelector('.empty-cart').style.display = 'none';
		document.querySelector('.non-empty-cart').style.display = 'flex';
	}
}

checkCart();

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

// method to format price values
const formatPrice = (listItem) => {
	let unitPrice = listItem.querySelector('.product-unit-price');
	let unitPriceValue = parseFloat(unitPrice.getAttribute('data-price'));
	unitPrice.textContent = formatter.format(unitPriceValue);

	let qty = parseInt(listItem.querySelector('.product-qty').textContent);

	let subTotalPrice = listItem.querySelector('.cart-subtotal');
	subTotalPrice.textContent = formatter.format(qty * unitPriceValue);
}

// format price values
for(const listItem of cartItems) {
	formatPrice(listItem);
}

// make requests
const makeRequest = (url, method, postObj, callBack, listItem) => {
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
        try{
			callBack(data, listItem);
		}catch(err){
			console.log('no call back fired for', url, 'request');
		}
    })
    .catch((error) => {
        console.error('Error:', error);
    });
}

// update cart
const updateCart = () => {
	// make array to store objects
	let postObj = [];
	let cartItems = document.querySelectorAll('.cart-list-item');
	for(const cartItem of cartItems) {
		// make post objects
		let productId = parseInt(cartItem.querySelector('.cart-item-data').getAttribute('data-productId'));
		let qty = parseInt(cartItem.querySelector('.product-qty').textContent);
		
		let cartItemData = {
			id: productId,
			qty: qty
		}
	
		// add to array
		postObj.push(cartItemData);
	}
	
	// make request
	makeRequest('update-cart', 'POST', postObj, null, null);
}

// get cart item list
let productList = document.querySelector('.product-list');

// add event listener
productList.addEventListener('click', (event) => {
	// set up list item element and productId
	let listItem;
	let productId;
	
	// if remove button is clicked
	 if(event.target.className === 'remove-item-button'){
		listItem = event.target.parentElement.parentElement.parentElement;
		productId = getId(listItem);
		
		postObj = {id: productId};
		
		removeItem(listItem, postObj);
	}else if(event.target.className === 'increment-button'){
		listItem = event.target.parentElement.parentElement;
		let productQty = getQty(listItem);
		productQty.textContent++;
		updateSubTotalPrice(listItem, productQty.textContent);
		
	}else if(event.target.className === 'decrement-button'){
		listItem = event.target.parentElement.parentElement;
		let productQty = getQty(listItem);
		productId = getId(listItem);
		if(productQty.textContent == 1) {
			if(confirm('Cannot decrement quantity further\nWant to remove item') == true){
				removeItem(listItem, {id: productId});
			}
		}else {
			productQty.textContent--;
			updateSubTotalPrice(listItem, productQty.textContent);
		}
	}
});

// remove item from cart
const removeItem = (listItem, postObj) => {
	makeRequest('remove-item', 'POST', postObj, removeItemCallBack, listItem);
}

// method to call after removing item
const removeItemCallBack = (responseObj , listItem) => {
	listItem.remove();
	updateCartTotal();
	checkCart();
}

// update subtotal price
const updateSubTotalPrice = (listItem, qty) => {
	let unitPrice = listItem.querySelector('.product-unit-price').getAttribute('data-price');
	let subTotal = listItem.querySelector('.cart-subtotal');
	subTotal.textContent = formatter.format(unitPrice * qty);
	updateCartTotal();
}

// get quantity
const getQty = (listItem) => {
	let productQty = listItem.querySelector('.product-qty');
	return productQty;
}

// get product id
const getId = (listItem) => {
	return parseInt(listItem.querySelector('.cart-item-data').getAttribute('data-productId'));
}
