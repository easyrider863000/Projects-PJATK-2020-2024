function validateForm(){
    const nameInput = document.getElementById("name");
    const descriptionInput = document.getElementById("description");
    const employeInput = document.getElementById("employe");
    const projectInput = document.getElementById("project");

    const errorName = document.getElementById("errorName");
    const errorDescription = document.getElementById("errorDescription");
    const errorEmploye = document.getElementById("errorEmploye");
    const errorProject = document.getElementById("errorProject");

    const errorsSummary = document.getElementById("errorsSummary");

    resetErrors([nameInput,descriptionInput,employeInput,projectInput],
        [errorName,errorProject,errorDescription,errorEmploye],
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
    if(!checkRequired(employeInput.value)){
        valid = false;
        errorEmploye.innerText = "Pole jest wymagane";
    }
    if(!checkRequired(projectInput.value)){
        valid = false;
        errorProject.innerText = "Pole jest wymagane";
    }

    if(!valid){
        errorsSummary.innerText = "Formularz zawiera bledy";
    }

    return valid;
}