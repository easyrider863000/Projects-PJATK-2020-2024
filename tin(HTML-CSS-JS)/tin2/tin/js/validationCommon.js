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

function checkEmail(value) {
    if (!value) {
        return false;
    }
    value = value.toString().trim();
    const re = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(([^<>()[\]\.,;:\s@\"]+\.)+[^<>()[\]\.,;:\s@\"]{2,})$/i;
    return re.test(value);
}

function checkPhoneNumber(value) {
    if (!value) {
        return false;
    }
    value = value.toString().trim();
    const re = /(?<!\w)(\(?(\+|00)?48\)?)?[ -]?\d{3}[ -]?\d{3}[ -]?\d{3}(?!\w)/;
    return re.test(value);
}

function checkDateIfAfter(value,compareTo) {
    if(!value){
        return false;
    }
    if(!compareTo){
        return false;
    }
    const pattern = /(\d{4})-(\d{2})-(\d{2})/;
    if(!pattern.test(value)){
        return false;
    }
    if(!pattern.test(compareTo)){
        return false;
    }
    const valueDate = new Date(value);
    const compareToDate = new Date(compareTo);
    if(valueDate.getTime()<compareToDate.getTime()){
        return false;
    }
    return true;
}

function checkDateIfNotFuture(value) {
    let nowDate = new Date(),
    month = ''+(nowDate.getMonth()+1),
    day = ''+nowDate.getDate(),
    year = ''+nowDate.getFullYear();
    if(month.length<2){
        month = '0'+month;
    }
    if(day.length<2){
        day = '0'+day;
    }
    const nowString = [year, month, day].join('-');
    return checkDateIfAfter(value,nowString);
}

function checkNumber(value){
    if(!value){
        return false;
    }
    if(isNaN(value)){
        return false;
    }
    return true;
}