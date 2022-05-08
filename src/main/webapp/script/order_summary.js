// currency formatter
let formatter = new Intl.NumberFormat('en-IN', { style: 'currency', currency: 'INR' });

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

// get unit price element for all products
let unitPriceElements = document.querySelectorAll('.product-unit-price');

// get subTotal price element for all products
let subTotalElements = document.querySelectorAll('.item-subtotal');

// format all unit price elemetns
for(const product of unitPriceElements){
	product.textContent = formatter.format(product.getAttribute('data-price'));
}

// format all subtotal price elements
for(const subTotal of subTotalElements){
	subTotal.textContent = formatter.format(subTotal.getAttribute('data-price'));
}

updateCartTotal();