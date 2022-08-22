const br = document.createElement("br");
const form = document.createElement("form");
const email = document.createElement("input");
const name = document.createElement("input");
const phone = document.createElement("input");
const submitButton = document.createElement("input");
const billingAddress = document.createElement("input");
const shippingAddress = document.createElement("input");
const hr = document.createElement("hr");
const checkoutButton = document.getElementById("checkout");

checkoutButton.addEventListener("click", addCheckOutForm, {once:true});

function addCheckOutForm() {
    appendElements();
    setInputAttributes();
    addStyle();
}

function appendElements() {
    form.appendChild(name);
    form.appendChild(br.cloneNode());
    form.appendChild(email);
    form.appendChild(br.cloneNode());
    form.appendChild(phone);
    form.appendChild(br.cloneNode());
    form.appendChild(billingAddress);
    form.appendChild(br.cloneNode());
    form.appendChild(shippingAddress);
    form.appendChild(br.cloneNode());
    form.appendChild(submitButton);
    form.appendChild(br.cloneNode());
    document.getElementById("checkout-form").appendChild(hr);
    document.getElementById("checkout-form").appendChild(form);
}

function setInputAttributes() {
    name.setAttribute("type", "text");
    name.setAttribute("placeholder", "Name");
    email.setAttribute("type", "email");
    email.setAttribute("placeholder", "E-Mail");
    phone.setAttribute("type", "tel");
    phone.setAttribute("placeholder", "Phone");
    billingAddress.setAttribute("type", "string");
    billingAddress.setAttribute("placeholder", "Billing address");
    shippingAddress.setAttribute("type", "string");
    shippingAddress.setAttribute("placeholder", "Shipping address");
    submitButton.setAttribute("type", "submit");
    submitButton.setAttribute("value", "Go to payment");
}

function addStyle() {
    form.style.backgroundColor = "#eae8e8";
    form.style.paddingTop = "25px";
    submitButton.setAttribute("class", "btn btn-dark btn-block btn-lg");
    hr.setAttribute("class", "my-4");
    for (let i = 0; i < form.length-1; i++) {
        form[i].setAttribute("class", "form-control");
    }
}