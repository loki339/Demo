import React, { useState } from "react";
import { Link } from "react-router-dom";
import { useNavigate } from "react-router-dom";

export default function SignupBody() {
  let [firstName, setFirstName] = useState("");
  let [lastName, setLastName] = useState("");
  let [email, setEmail] = useState("");
  let [mobile, setMobile] = useState("");
  let [contactAddress, setContactAddress] = useState("");
  let [city, setCity] = useState("");
  let [state, setState] = useState("");
  let [zipCode, setZipCode] = useState("");
  let [password, setPassword] = useState("");
  let [confirmPassword, setConfirmPassword] = useState("");

  let navigate = useNavigate();

  const addCustomer = async () => {
    await fetch("http://localhost:8090/addCustomer", {
      method: "POST",
      body: JSON.stringify({
        firstName: firstName,
        lastName: lastName,
        contactAddress: contactAddress,
        city: city,
        state: state,
        zipcode: zipCode,
        contactMobile: mobile,
        emailId: email,
        password: password,
      }),
      headers: {
        "content-type": "application/json; charset=UTF-8",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        if (data) {
          navigate("/customerlogin");
        } else {
          alert("Signup Failed !!!");
        }
      })
      .catch((err) => {
        console.error("Error : " + err.message);
        alert("signup success");
        navigate("/customerlogin");
      });
  };

  let handleSubmit = (e) => {
    e.preventDefault();
    if (password === confirmPassword && password !== "") {
      addCustomer();
    } else {
      alert("Passwords Doesnot match");
    }
  };

  return (
    <div className="container mt-5 mb-5">
      <div className="card">
        <div className="card-header">Signup</div>
        <div className="card-body">
          <form className="form-floating" onSubmit={handleSubmit}>
            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingFirstName"
                placeholder="Enter First Name"
                onChange={(e) => {
                  setFirstName(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingFirstName">First Name</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingLastName"
                placeholder="Enter Last Name"
                onChange={(e) => {
                  setLastName(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingLastName">Last Name</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingEmail"
                placeholder="Enter Email Address"
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingEmail">Email Address</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingMobile"
                placeholder="Enter Mobile Number"
                onChange={(e) => {
                  setMobile(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingMobile">Mobile Number</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingContactAddress"
                placeholder="Enter Contact Address"
                onChange={(e) => {
                  setContactAddress(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingMobile">Contact Address</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingCity"
                placeholder="Enter City"
                onChange={(e) => {
                  setCity(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingMobile">City</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingState"
                placeholder="Enter State"
                onChange={(e) => {
                  setState(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingMobile">State</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingZipCode"
                placeholder="Enter Zip Code"
                onChange={(e) => {
                  setZipCode(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingMobile">Zip Code</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="password"
                className="form-control"
                id="floatingPassword"
                placeholder="Password"
                onChange={(e) => {
                  setPassword(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingPassword">Password</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="password"
                className="form-control"
                id="floatingConfirmPassword"
                placeholder="Confirm Password"
                onChange={(e) => {
                  setConfirmPassword(e.target.value);
                }}
              />
              <label htmlhtmlFor="floatingConfirmPassword">
                Confirm Password
              </label>
            </div>

            <p>
              <Link
                className="link-offset-2 link-underline link-underline-opacity-0"
                to="/customerlogin"
              >
                Have an account? Login
              </Link>
            </p>

            <button type="submit" className="btn btn-primary text-center">
              Signup
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
