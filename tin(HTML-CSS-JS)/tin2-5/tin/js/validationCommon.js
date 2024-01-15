function resetErrors(inputs, errorTexts, errorInfo){
    for(let i = 0;i<inputs.length;i++){
        inputs[i].classList.remove("error-input");
    }
    for(let i = 0;i<errorTexts.length;i++){
        errorTexts[i].innerText = "";
    }
    errorInfo.innerText = "";
}

function checkRequired(value){
    if (!value){
        return false;
    }
    value = value.toString().trim();
    if(value===""){
        return false;
    }
    return true;
}