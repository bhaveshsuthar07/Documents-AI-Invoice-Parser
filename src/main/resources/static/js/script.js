const form = document.getElementById('myForm');
const submitButton = form.querySelector('button[type="submit"]');

form.addEventListener('keyup', () => {
    const allFilled = [...form.elements].every(el => el.value);// Check if all elements have values

    console.log(allFilled);

    submitButton.disabled = allFilled; // Enable button only if all filled
});


const myDiv = document.getElementById("myDiv");
const toggleButton = document.getElementById("sub");
myDiv.classList.remove("spinner");
toggleButton.addEventListener("click", function() {
    myDiv.classList.add("spinner");
    // toggleButton.disabled=true;
    setTimeout(delay,500);
});

function delay(){
    toggleButton.disabled=true;
}
