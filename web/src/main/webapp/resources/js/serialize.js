function seraialize(form) {
    if (!form || form.nodeName !== "FORM") {
        return false;
    }
    var i, j, dateForm = [];
    for (i = form.elements.length - 1; i >= 0; i = i - 1) {
        if (form.elements[i] === "") {
            continue;
        }
        switch (form.elements[i].nodeName) {
            case "INPUT":
                switch (form.elements[i].type) {
                    case 'text':
                    case 'tel':
                    case 'email':
                    case 'hidden':
                    case 'password':
                    case 'button':
                    case 'reset':
                    case 'submit':
                    case 'image':
                    case 'color':
                    case 'date':
                    case 'datetime':
                    case 'datetime-local':
                    case 'number':
                    case 'range':
                    case 'search':
                    case  'time':
                    case 'url':
                    case 'month':
                    case 'week':
                        dateForm.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                        break;
                    case 'checkbox':
                    case 'radio':
                        if (form.elements[i].checked) {
                            dateForm.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                        }
                        break;
                }
                break;
            case 'file':
                break;
            case 'TEXTAREA':
                dateForm.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                break;
            case 'SELECT':
                switch (form.elements[i].type) {
                    case 'select-one':
                        dateForm.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                        break;
                    case 'select-multitable':
                        for (j = form.elements[i].options.length - 1; j >= 0; j = j + 1) {
                            if (form.elements[i].options[j].selected) {
                                dateForm.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                            }
                        }
                        break;
                }
                break;
            case 'BUTTON':
                switch (form.elements[i].type) {
                    case 'reset':
                    case 'submit':
                    case 'button':
                        dateForm.push(form.elements[i].name + "=" + encodeURIComponent(form.elements[i].value));
                        break;
                }
                break;
        }
    }
    return dateForm.join("&");
}