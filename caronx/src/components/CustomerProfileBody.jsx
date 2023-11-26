import React from "react";
import { useState } from "react";
import { useEffect } from "react";
import { useContext } from "react";
import { data } from "../App";
import { Link } from "react-router-dom";

export default function CustomerProfileBody() {
  const customer = useContext(data);
  const [profileData, setProfileData] = useState(null);
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
        } else {
          console.error("Failed to fetch data");
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, [customerId]);

  return (
    profileData && (
      <div className="container mt-2 mb-5">
        <div className="container d-flex align-items-center">
          <img
            className="mx-auto"
            src="https://cdn-icons-png.flaticon.com/512/5087/5087579.png"
            alt="Network Error..."
            style={{ width: "12rem" }}
          />
        </div>
        <div className="card border-dark mt-3">
          <div className="card-header">Profile</div>
          <div className="card-body">
            <table className="table">
              <tbody>
                <tr>
                  <th scope="row">Customer ID</th>
                  <td>{profileData.customerId}</td>
                </tr>
                <tr>
                  <th scope="row">First Name</th>
                  <td>{profileData.firstName}</td>
                </tr>
                <tr>
                  <th scope="row">Last Name</th>
                  <td>{profileData.lastName}</td>
                </tr>
                <tr>
                  <th scope="row">Mobile Number</th>
                  <td>{profileData.contactMobile}</td>
                </tr>
                <tr>
                  <th scope="row">Email Address</th>
                  <td>{profileData.emailId}</td>
                </tr>
                <tr>
                  <th scope="row">Area</th>
                  <td>{profileData.contactAddress}</td>
                </tr>
                <tr>
                  <th scope="row">City</th>
                  <td>{profileData.city}</td>
                </tr>
                <tr>
                  <th scope="row">State</th>
                  <td>{profileData.state}</td>
                </tr>
                <tr>
                  <th scope="row">Zip Code</th>
                  <td>{profileData.zipcode}</td>
                </tr>
              </tbody>
            </table>
            <Link to="/customer/editprofile" className="btn btn-primary">
              Edit Profile
            </Link>
          </div>
        </div>
      </div>
    )
  );
}
