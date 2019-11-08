 
    //Сохраняем в переменной ссылку на элемент селекта

var select, group1, group2, group3, groups
 //Сохраняем в переменной ссылку на элемент селекта






function activateTab() {

    select = document.getElementById('select');
    group1 = document.getElementById('group1');
    group2 = document.getElementById('group2');
    group3 = document.getElementById('group3');
    groups = {
        'группа 1': group1,
        'группа 2': group2,
        'группа 3': group3,
    };


     var selectedOption = select.options[select.selectedIndex].value
     var groupsKeys = Object.keys(groups)

     for(var i = 0; i < groupsKeys.length; i++) {
         var currentGroupKey = groupsKeys[i]
         var currentGroupElement = groups[currentGroupKey]
         currentGroupElement.style.display = 'none'
     }

     groups[selectedOption].style.display = 'block'

     var test =  document.getElementById('test');
     // var string = `
     // <p>Тестовое значение</p>
     // <p>${selectedOption}</p>
     // <p>ещё одна хрень</p>
     // `
     test.innerHTML = string
}   

 
 