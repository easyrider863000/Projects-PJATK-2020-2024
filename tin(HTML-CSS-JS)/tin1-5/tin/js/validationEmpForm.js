function validateForm(){
    const firstNameInput = document.getElementById("firstName");
    const secondNameInput = document.getElementById("secondName");
    const emailInput = document.getElementById("email");
    const phoneNumberInput = document.getElementById("phoneNumber");
    const birthDateInput = document.getElementById("birthdate");

    const errorFirstName = document.getElementById("errorFirstName");
    const errorSecondName = document.getElementById("errorSecondName");
    const errorEmail = document.getElementById("errorEmail");
    const errorPhoneNumber = document.getElementById("errorPhoneNumber");
    const errorBirthdate = document.getElementById("errorBirthdate");

    const errorsSummary = document.getElementById("errorsSummary");

    resetErrors([firstNameInput,secondNameInput,emailInput,phoneNumberInput,birthDateInput],
        [errorFirstName,errorSecondName,errorEmail,errorPhoneNumber,errorBirthdate],
        errorsSummary);

    let valid = true;

    if(!checkRequired(firstNameInput.value)){
        valid = false;
        errorFirstName.innerText = "Pole jest wymagane";
    }
    if(!checkRequired(secondNameInput.value)){
        valid = false;
        errorSecondName.innerText = "Pole jest wymagane";
    }
    if(!checkRequired(emailInput.value)){
        valid = false;
        errorEmail.innerText = "Pole jest wymagane";
    }
    else if(!checkEmail(emailInput.value)){
        valid = false;
        errorEmail.innerText = "Nie jest emailem";
    }
    if(!checkRequired(phoneNumberInput.value)){
        valid = false;
        errorPhoneNumber.innerText = "Pole jest wymagane";
    }
    else if(!checkPhoneNumber(phoneNumberInput.value)){
        valid = false;
        errorPhoneNumber.innerText = "Nie jest telefonem";
    }
    if(checkDateIfNotFuture(birthDateInput.value)){
        valid = false;
        errorBirthdate.innerText = "Nie jest data urodzenia";
    }


    if(!valid){
        errorsSummary.innerText = "Formularz zawiera bledy";
    }

    return valid;
}