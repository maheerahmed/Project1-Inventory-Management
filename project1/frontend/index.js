const URL = "http://localhost:8282/stock"
let allStock = [];

document.addEventListener('DOMContentLoaded', () => {
    
    let xhr = new XMLHttpRequest();

    
    xhr.onreadystatechange = () => {
        if(xhr.readyState == 4){
            let stock = JSON.parse(xhr.responseText);

            stock.forEach(newStock => {
                addStockToTable(newStock);
            });

        }


    }
    xhr.open('GET', URL);
    xhr.send();

});

function addStockToTable(newStock){
    let tr = document.createElement('tr');
    let id = document.createElement('td');
    let name = document.createElement('td');
    let quantity = document.createElement('td');
    let warehouse = document.createElement('td');
    let editBtn = document.createElement('td');
    let deleteBtn = document.createElement('td');

    id.innerText = newStock.id;
    name.innerText = newStock.stock_name;
    quantity.innerText = newStock.quantity;
    warehouse.innerText = newStock.warehouse.warehouse_name;

    editBtn.innerHTML =
    `<button class="btn btn-primary" id="edit-button" onclick="activateEditForm(${newStock.id})">Edit</button>`

    deleteBtn.innerHTML =
    `<button class="btn btn-primary" id="edit-button" onclick="activateDeleteForm(${newStock.id})">Delete</button>`

    tr.appendChild(id);
    tr.appendChild(name);
    tr.appendChild(quantity);
    tr.appendChild(warehouse);
    tr.appendChild(editBtn);
    tr.appendChild(deleteBtn);
    
    tr.setAttribute('id', `TR${newStock.id}`);

    //tr.setAttribute('id','TR', newStock.id);

    document.getElementById('stock-table-body').appendChild(tr);

    allStock.push(newStock);
}


document.getElementById('new-stock-form').addEventListener('submit', (event) => {
    event.preventDefault();
    let inputData = new FormData(document.getElementById('new-stock-form'));

    let newStock = {
        stock_name : inputData.get('new-stock-stock_name'),
        quantity : inputData.get('new-stock-quantity'),
        warehouse : {
            warehouse_name : inputData.get('new-warehouse-warehouse_name'),
        }
    }

    doPostRequest(newStock);

})

async function doPostRequest(newStock){
    let returneData = await fetch(URL + '/stock', {
        method : 'POST', 
        headers : {
            "Content-Type" : 'application/json'
        },
        body : JSON.stringify(newStock)
    });

    let stockJson = await returneData.json();
    console.log('STOCKJSON ' + stockJson );

    addStockToTable(stockJson);

    document.getElementById('new-stock-form').reset();
}

document.getElementById('update-cancel-button').addEventListener('click', (event) => {
    event.preventDefault();
    resetAllForms();
});

document.getElementById('delete-cancel-button').addEventListener('click', (event) => {
    event.preventDefault();
    resetAllForms();
    
});

function resetAllForms() {

    // clears data from all forms
    document.getElementById('new-stock-form').reset();
    document.getElementById('update-stock-form').reset();
    document.getElementById('delete-stock-form').reset();

    // dispalys only the new-stock-form
    document.getElementById('new-stock-form').style.display = 'block';
    document.getElementById('update-stock-form').style.display = 'none';
    document.getElementById('delete-stock-form').style.display = 'none'; 
}

    
function activateEditForm(stockId){
    const selectedStock = allStock.find(stock => stock.id === stockId);

    if (selectedStock) {
        document.getElementById('update-stock-id').value = selectedStock.id;
        document.getElementById('update-stock-stock_name').value = selectedStock.stock_name;
        document.getElementById('update-stock-quantity').value = selectedStock.quantity;
        document.getElementById('update-warehouse-id').value = selectedStock.warehouse.id;
        document.getElementById('update-warehouse-warehouse_name').value = selectedStock.warehouse.warehouse_name;
    }
    //showing edit form

    document.getElementById('new-stock-form').style.display = 'none';
    document.getElementById('update-stock-form').style.display = 'block';
    document.getElementById('delete-stock-form').style.display = 'none';
}

function activateDeleteForm(stockId){
    for(let s of allStock){
        if (s.id === stockId){ 
            document.getElementById('delete-stock-id').value = s.id;
            document.getElementById('delete-stock-stock_name').value = s.stock_name;
            document.getElementById('delete-stock-quantity').value = s.quantity;
            document.getElementById('delete-warehouse-id').value = s.warehouse.id;
            document.getElementById('delete-warehouse-warehouse_name').value = s.warehouse.warehouse_name;
        }
    }
    //showing delete form

    document.getElementById('new-stock-form').style.display = 'none';
    document.getElementById('update-stock-form').style.display = 'none';
    document.getElementById('delete-stock-form').style.display = 'block';
}



document.getElementById('update-stock-form').addEventListener('submit', (event) => {
    event.preventDefault();		// prevent default form actions from occuring

    // retrieving data from the update form
    let inputData = new FormData(document.getElementById('update-stock-form'));

    
    let stock = {
        id : document.getElementById('update-stock-id').value,    
        stock_name : inputData.get('update-stock-stock_name'),         
        quantity : inputData.get('update-stock-quantity'),
        warehouse : {
            id : document.getElementById('update-warehouse-id').value,
            warehouse_name : inputData.get('update-warehouse-warehouse_name'),
        }
    }


    fetch(URL + '/stock', {
        method : 'PUT',
        headers: {
            "Content-Type": "application/json",
        },
        body : JSON.stringify(stock)
    })
    .then((data) => {
        
        return data.json();
    })
    .then((stockJson) => {          
        updateStockInTable(stockJson);

        // reset the forms
        document.getElementById('update-stock-form').reset();
        document.getElementById('new-stock-form').style.display = 'block';
        document.getElementById('update-stock-form').style.display = 'none';

        
    })
    .catch((error) => {
        

        console.error(error);   
    })
});


function updateStockInTable(stock) {
    const rowId = `stock-${stock.id}`;
  const row = document.getElementById(rowId);
  if (row) {
    row.innerHTML = `
      <td>${stock.id}</td>
      <td>${stock.stock_name}</td>
      <td>${stock.quantity}</td>
      <td>${stock.warehouse.warehouse_name}</td>
      <td><button class="btn btn-primary" id="editButton" onclick="activateEditForm(${stock.id})">Edit</button></td>
      <td><button class="btn btn-primary" id="deleteButton" onclick="activateDeleteForm(${stock.id})">Delete</button></td>
    `;
  }
  }


document.getElementById('delete-stock-form').addEventListener('submit', (event) => {
    event.preventDefault();		// prevent default form actions from occuring


    // get the data from the form since all the fields are disabled and FormData won't capture them
    let stockId = document.getElementById('delete-stock-id').value;
    let nameOnForm = document.getElementById('delete-stock-stock_name').value;		
    let quantityOnForm = document.getElementById('delete-stock-quantity').value;	
    let warehouseId = document.getElementById('delete-warehouse-id').value;
    let warehouseNameOnForm = document.getElementById('delete-warehouse-warehouse_name').value;

    let stock = {
        id : stockId,
        stock_name : nameOnForm,
        quantity : quantityOnForm,
        warehouse : {
            id : warehouseId,
            warehouse_name : warehouseNameOnForm,
        }
    };

    // sending delete request
    fetch(URL + '/stock', {
        method : 'DELETE',
        headers: {
            "Content-Type": "application/json",
        },
        body : JSON.stringify(stock)
    })
    .then((data) => {

        
        if(data.status === 204) {
            // remove stock from table
            removeStockFromTable(stock);

            // resetting all forms
            resetAllForms();
        }
    })
    .catch((error) => {
        console.error(error);   
    })

});

function removeStockFromTable(stock) {

    // removing the <tr> from the table when a stock gets deleted
    const element = document.getElementById(`TR${stock.id}`);
    element.remove();
}
