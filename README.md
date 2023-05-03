# data_analytics_project

## API Endpoints

### /products
parameters: "List ID" (?id={listID})

Method: Get

response: Array with "Name", "id" and "amount" of Products in list

Description:
Displays all products that are in stock.

### /product


#### ADD
parameters: "List ID", "Product Name", "amount"

Method: POST

response: Successfully!

Description:
Product will be added to the according list


#### EDIT
parameters: "List ID", "Product ID", "Product Name", "amount"

Method: PUT

response: Successfully EDITED!

Description:
Product will be edited accordingly.


#### DELETE
parameters: "List ID", "Product ID"

Method: DELETE

response: Successfully DELETED!

Description:
Product will be deleted on the according list.