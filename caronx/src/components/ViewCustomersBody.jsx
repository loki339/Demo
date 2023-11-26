import React, { useEffect, useState } from "react";

export default function ViewCustomersBody() {
  const [data, setData] = useState(null);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch("http://localhost:8090/getallCustomers");
        if (response.ok) {
          const result = await response.json();
          setData(result);
        } else {
          console.error("Failed to fetch data");
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, []);

  const deleteCustomer = async (customerId) => {
    try {
      const response = await fetch(
        `http://localhost:8090/deleteCustomer/${customerId}`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );

      if (response.ok) {
        setData((prevData) =>
          prevData.filter((customer) => customer.customerId !== customerId)
        );
      } else {
        console.error("Failed to delete customer");
      }
    } catch (error) {
      console.error("Error deleting customer:", error);
    }
  };

  return (
    <>
      <div className="container mt-4 mb-5 text-light">
        <h2>Customers...</h2>
        <table className="table table-hover table-bordered border-secondary mt-4">
          <thead>
            <tr>
              <th scope="col">CID</th>
              <th scope="col">FirstName</th>
              <th scope="col">LastName</th>
              <th scope="col">Mobile</th>
              <th scope="col">EmailAddress</th>
              <th scope="col">Area</th>
              <th scope="col">City</th>
              <th scope="col">State</th>
              <th scope="col">ZipCode</th>
              <th scope="col" className=" text-center">
                Delete
              </th>
            </tr>
          </thead>
          <tbody>
            {data &&
              data.map((customer, index) => (
                <tr key={index}>
                  <th scope="row">{customer.customerId}</th>
                  <td>{customer.firstName}</td>
                  <td>{customer.lastName}</td>
                  <td>{customer.contactMobile}</td>
                  <td>{customer.emailId}</td>
                  <td>{customer.contactAddress}</td>
                  <td>{customer.city}</td>
                  <td>{customer.state}</td>
                  <td>{customer.zipcode}</td>
                  <td className=" text-center">
                    <button
                      className="btn btn-sm btn-danger"
                      data-bs-toggle="modal"
                      data-bs-target={`#exampleModal${customer.customerId}`}
                    >
                      Delete
                    </button>
                    <div
                      className="modal fade"
                      id={`exampleModal${customer.customerId}`}
                      tabIndex="-1"
                      aria-labelledby="exampleModalLabel"
                      aria-hidden="true"
                    >
                      <div className="modal-dialog">
                        <div className="modal-content">
                          <div className="modal-header">
                            <h1
                              className="modal-title fs-5"
                              id="exampleModalLabel"
                            >
                              Delete
                            </h1>
                            <button
                              type="button"
                              className="btn-close"
                              data-bs-dismiss="modal"
                              aria-label="Close"
                            ></button>
                          </div>
                          <div className="modal-body text-start">
                            Do yo want to delete the customer with the ID{" - "}
                            <span className="text-danger">
                              {customer.customerId}
                            </span>
                          </div>
                          <div className="modal-footer">
                            <button
                              type="button"
                              className="btn btn-secondary"
                              data-bs-dismiss="modal"
                            >
                              Close
                            </button>
                            <button
                              type="button"
                              className="btn btn-danger"
                              data-bs-dismiss="modal"
                              onClick={() => {
                                deleteCustomer(customer.customerId);
                              }}
                            >
                              Delete
                            </button>
                          </div>
                        </div>
                      </div>
                    </div>
                  </td>
                </tr>
              ))}
          </tbody>
        </table>
      </div>
    </>
  );
}
