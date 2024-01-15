function validateForm(){
    const nameInput = document.getElementById("name");
    const descriptionInput = document.getElementById("description");
    const taskCountInput = document.getElementById("taskCount");

    const errorName = document.getElementById("errorName");
    const errorDescription = document.getElementById("errorDescription");
    const errorTaskCount = document.getElementById("errorTaskCount");

    const errorsSummary = document.getElementById("errorsSummary");

    resetErrors([nameInput, descriptionInput, taskCountInput],
        [errorName, errorDescription, errorTaskCount],
        errorsSummary);

    let valid = true;

    if(!checkRequired(nameInput.value)){
        valid = false;
        errorName.innerText = "Pole jest wymagane";
    }
    if(!checkRequired(descriptionInput.value)){
        valid = false;
        errorDescription.innerText = "Pole jest wymagane";
    }
    if(!checkRequired(taskCountInput.value)){
        valid = false;
        errorTaskCount.innerText = "Pole jest wymagane";
    }
    else if(!checkNumber(taskCountInput.value)){
        valid = false;
        errorTaskCount.innerText = "Nie jest numerem";
    }


    if(!valid){
        errorsSummary.innerText = "Formularz zawiera bledy";
    }

    return valid;
}