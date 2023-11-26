import React from "react";
import { data } from "../App";
import { useState } from "react";
import { useContext } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import CustomerNavbar from "./CustomerNavbar";

export default function PaymentShipment() {
  const { search } = useLocation();
  const params = new URLSearchParams(search);
  const customer = useContext(data);
  const navigate = useNavigate();

  let [shippingAddress, setShippingAddress] = useState("");
  let [cardNumber, setCardNumber] = useState("");
  let [expDate, setExpDate] = useState("");
  let [cvvNumber, setCvvNumber] = useState("");

  const payment = async (carId, cost, date, customerId) => {
    const getDataObject = () => {
      const dataObject = {
        transactionDate: date,
        cost: cost,
        cardNumber: cardNumber,
        carId: carId,
        customerId: customerId,
        shippingAddress: shippingAddress,
      };
      return dataObject;
    };

    const para = new URLSearchParams({
      cvvNumber: cvvNumber,
      expiryDate: expDate.replace("/", "-"),
    });

    await fetch(`http://localhost:8090/addCarSale?${para}`, {
      method: "POST",
      body: JSON.stringify(getDataObject()),
      headers: {
        "content-type": "application/json; charset=UTF-8",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        if (data) {
          alert("Car Purchased Successfully");
          navigate("/customer/purchasedcars");
        } else {
          alert("Signup Failed !!!");
        }
      })
      .catch((err) => {
        console.error("Error : " + err.message);
      });
  };

  let handleSubmit = (e) => {
    e.preventDefault();
    const date = new Date();
    payment(
      Number(params.get("carid")),
      Number(params.get("cost")),
      date,
      customer.customerId
    );
  };

  return (
    <>
      <CustomerNavbar />
      <div className="container mt-5 mb-5">
        <div className="card">
          <h5 className="card-header">Shipping and Payment</h5>

          <div className="card-body">
            <form className="form-floating" onSubmit={handleSubmit}>
              <div className="row">
                <div className="col-md-4">
                  <div className="form-floating mt-2 mb-2">
                    <input
                      type="number"
                      className="form-control"
                      id="floatingModel"
                      placeholder="Card Number"
                      onChange={(e) => {
                        setCardNumber(e.target.value);
                      }}
                    />
                    <label htmlFor="floatingModel">Card Number</label>
                  </div>
                </div>

                <div className="col-md-4">
                  <div className="form-floating mt-2 mb-2">
                    <input
                      type="text"
                      className="form-control"
                      id="floatingModelYear"
                      placeholder="Expiry Date"
                      onChange={(e) => {
                        setExpDate(e.target.value);
                      }}
                    />
                    <label htmlFor="floatingModel">Expiry Date</label>
                  </div>
                </div>

                <div className="col-md-4">
                  <div className="form-floating mt-2 mb-2">
                    <input
                      type="number"
                      className="form-control"
                      id="floatingCost"
                      placeholder="CVV Number"
                      onChange={(e) => {
                        setCvvNumber(e.target.value);
                      }}
                    />
                    <label htmlFor="floatingCost">CVV Number</label>
                  </div>
                </div>
              </div>

              <div className="row">
                <div className="col-md-12">
                  <div className="form-floating mt-2 mb-2">
                    <textarea
                      className="form-control"
                      placeholder="Shipping Address"
                      id="floatingCarDescription"
                      style={{ height: "100px" }}
                      onChange={(e) => {
                        setShippingAddress(e.target.value);
                      }}
                    ></textarea>
                    <label htmlFor="floatingCarDescription">
                      Shipping Address
                    </label>
                  </div>
                </div>
              </div>

              <button type="submit" className="btn btn-primary mt-2 mb-1">
                Place Order
              </button>
            </form>
          </div>
        </div>
      </div>
    </>
  );
}
