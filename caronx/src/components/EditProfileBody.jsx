import React from "react";
import { useState } from "react";
import { data } from "../App";
import { useContext } from "react";
import { useEffect } from "react";
import { useNavigate } from "react-router-dom";

export default function EditProfileBody() {
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
  let [profileData, setProfileData] = useState(null);

  let navigate = useNavigate();
  const customer = useContext(data);
  const customerId = customer.customerId;
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8090/getCustomer/${customerId}`
        );
        if (response.ok) {
          const result = await response.json();
          setProfileData(result);
          setFirstName(result.firstName);
          setLastName(result.lastName);
          setEmail(result.emailId);
          setMobile(result.contactMobile);
          setContactAddress(result.contactAddress);
          setCity(result.city);
          setState(result.state);
          setZipCode(result.zipcode);
          setPassword(result.password);
          setConfirmPassword(result.password);
        } else {
          console.error("Failed to fetch data");
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, [customerId]);

  const editCustomer = async () => {
    await fetch(
      `http://localhost:8090/updateCustomer/${profileData.customerId}`,
      {
        method: "PUT",
        body: JSON.stringify({
          customerId: profileData.customerId,
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
      }
    )
      .then((response) => response.json())
      .then((data) => {
        if (data) {
          alert("Profile updated!!!");
          navigate("/customer/profile");
        } else {
          alert("Failed to edit profile!!!");
        }
      })
      .catch((err) => {
        console.error("Error : " + err.message);
      });
  };

  let handleSubmit = (e) => {
    e.preventDefault();
    if (password === confirmPassword) {
      editCustomer();
    } else {
      alert("Both passwords Doesnot match");
    }
  };

  return (
    <div className="container mt-5 mb-5">
      <div className="card">
        <div className="card-header">Edit Profile</div>
        <div className="card-body">
          <form className="form-floating" onSubmit={handleSubmit}>
            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingCustomerId"
                placeholder="Customer ID"
                disabled
                value={customer.customerId}
              />
              <label htmlhtmlFor="floatingFirstName">Customer ID</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingEmail"
                placeholder="Enter Email Address"
                value={email}
                onChange={(e) => {
                  setEmail(e.target.value);
                }}
                disabled
              />
              <label htmlhtmlFor="floatingEmail">Email Address</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="text"
                className="form-control"
                id="floatingFirstName"
                placeholder="Enter First Name"
                value={firstName}
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
                value={lastName}
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
                id="floatingMobile"
                placeholder="Enter Mobile Number"
                onChange={(e) => {
                  setMobile(e.target.value);
                }}
                value={mobile}
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
                value={contactAddress}
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
                value={city}
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
                value={state}
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
                value={zipCode}
              />
              <label htmlhtmlFor="floatingMobile">Zip Code</label>
            </div>

            <div className="form-floating mb-3">
              <input
                type="password"
                className="form-control"
                id="floatingPassword"
                placeholder="Password"
                value={password}
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
                value={confirmPassword}
              />
              <label htmlhtmlFor="floatingConfirmPassword">
                Confirm Password
              </label>
            </div>

            <button type="submit" className="btn btn-primary text-center">
              Save Changes
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
