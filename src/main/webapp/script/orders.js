// currency formatter
let formatter = new Intl.NumberFormat('en-IN', { style: 'currency', currency: 'INR' });

// get unit price element for all products
let unitPriceElements = document.querySelectorAll('.product-unit-price');

// get subTotal price element for all products
let subTotalElements = document.querySelectorAll('.item-subtotal');

// get order total elements for all products
let orderTotalElements = document.querySelectorAll('.order-total');

// format all unit price elemetns
for(const product of unitPriceElements){
	product.textContent = formatter.format(product.getAttribute('data-price'));
}

// format all subtotal price elements
for(const subTotal of subTotalElements){
	subTotal.textContent = formatter.format(subTotal.getAttribute('data-price'));
}

// format all order total elements
for(const orderTotal of orderTotalElements) {
	orderTotal.textContent = formatter.format(orderTotal.textContent);
}
