import React from "react";
import { useState } from "react";
import { useNavigate } from "react-router-dom";
import { useContext } from "react";
import { data } from "../App";

export default function SellCarBody() {
  let [company, setCompany] = useState("");
  let [model, setModel] = useState("");
  let [modelYear, setModelYear] = useState(0);
  let [fuelType, setFuelType] = useState("");
  let [carType, setCarType] = useState("");
  let [cost, setCost] = useState(0);
  let [availability, setAvailability] = useState("");
  let [image, setImage] = useState(null);
  let [description, setDescription] = useState("");

  let navigate = useNavigate();
  const customer = useContext(data);
  const customerId1 = customer.customerId;

  const addCarSubmit = async (date) => {
    const getRandom8DigitNumber = () => {
      const min = 10000000;
      const max = 99999999;
      return Math.floor(min + Math.random() * (max - min + 1));
    };
    const imageName = getRandom8DigitNumber() + image.name;
    const formData = new FormData();
    formData.append("carImage1", image);
    formData.append("imageName", imageName);

    try {
      const response = await fetch("http://localhost:8090/addNewCar", {
        method: "POST",
        body: formData,
      });
      console.log(response);
    } catch (error) {
      console.error("Error:", error);
    }

    await fetch("http://localhost:8090/addCar", {
      method: "POST",
      body: JSON.stringify({
        postDate: date,
        carType: carType,
        company: company,
        modelName: model,
        yearModel: modelYear,
        fuelType: fuelType,
        carDescription: description,
        cost: cost,
        carImage1: imageName,
        available: availability,
        customerId: customerId1,
      }),
      headers: {
        "content-type": "application/json; charset=UTF-8",
      },
    })
      .then((response) => response.json())
      .then((data) => {
        if (data) {
          navigate("/customer/sellcar");
          alert("Car Added");
        } else {
          console.log(data);
          alert("Failed in adding car...");
        }
      })
      .catch((err) => {
        console.log("Error : " + err.message);
        navigate("/customer/sellcar");
        alert("Car Added");
      });
  };

  let handleSubmit = (e) => {
    e.preventDefault();
    if (
      fuelType === "" ||
      carType === "" ||
      availability === "" ||
      image === null
    ) {
      alert("All fields are not filled");
    } else {
      const date = new Date();
      addCarSubmit(date);
    }
  };

  return (
    <div className="container mt-5 mb-5">
      <div className="card border-dark">
        <h5 className="card-header">Sell Your Car</h5>

        <div className="card-body">
          <form className="form-floating" onSubmit={handleSubmit}>
            <div className="row">
              <div className="col-md-6">
                <div className="form-floating mb-3">
                  <input
                    type="text"
                    className="form-control"
                    id="floatingCompany"
                    placeholder="Company"
                    onChange={(e) => {
                      setCompany(e.target.value);
                    }}
                  />
                  <label htmlFor="floatingCompany">Company</label>
                </div>
              </div>

              <div className="col-md-6">
                <div className="form-floating">
                  <input
                    type="text"
                    className="form-control"
                    id="floatingModel"
                    placeholder="Model"
                    onChange={(e) => {
                      setModel(e.target.value);
                    }}
                  />
                  <label htmlFor="floatingModel">Model</label>
                </div>
              </div>
            </div>

            <div className="row">
              <div className="col-md-4 mt-3">
                <div className="form-floating">
                  <input
                    type="number"
                    className="form-control"
                    id="floatingModelYear"
                    placeholder="Model Year"
                    onChange={(e) => {
                      setModelYear(e.target.value);
                    }}
                  />
                  <label htmlFor="floatingModel">Model Year</label>
                </div>
              </div>

              <div className="col-md-4 mt-3">
                <div className="form-floating">
                  <select
                    className="form-select"
                    id="floatingFuel"
                    aria-label="Floating label select example"
                    onChange={(e) => {
                      setFuelType(e.target.value);
                    }}
                  >
                    <option selected>Select Fuel Type</option>
                    <option value="petrol">Petrol</option>
                    <option value="diesel">Diesel</option>
                    <option value="cng">CNG</option>
                  </select>
                  <label htmlFor="floatingFuel">Fuel</label>
                </div>
              </div>

              <div className="col-md-4 mt-3">
                <div className="form-floating">
                  <select
                    className="form-select"
                    id="floatingCarType"
                    aria-label="Floating label select example"
                    onChange={(e) => {
                      setCarType(e.target.value);
                    }}
                  >
                    <option selected>Select Car Type</option>
                    <option value="new">New</option>
                    <option value="used">Used</option>
                    <option value="rare">Rare</option>
                  </select>
                  <label htmlFor="floatingCarType">Car Type</label>
                </div>
              </div>
            </div>

            <div className="row mb-3">
              <div className="col-md-4">
                <div className="form-floating mt-3">
                  <input
                    type="number"
                    className="form-control"
                    id="floatingCost"
                    placeholder="Model Year"
                    onChange={(e) => {
                      setCost(e.target.value);
                    }}
                  />
                  <label htmlFor="floatingCost">Cost</label>
                </div>
              </div>

              <div className="col-md-4">
                <div className="form-floating mt-3">
                  <select
                    className="form-select"
                    id="floatingAvailable"
                    aria-label="Floating label select example"
                    onChange={(e) => {
                      setAvailability(e.target.value);
                    }}
                  >
                    <option selected>Set Car Availability</option>
                    <option value="yes">Available</option>
                    <option value="no">Not Available</option>
                  </select>
                  <label htmlFor="floatingAvailable">Availability</label>
                </div>
              </div>

              <div className="col-md-4">
                <div className="mt-2">
                  <label htmlFor="formFile" className="form-label">
                    Car Image
                  </label>
                  <input
                    className="form-control form-control-sm"
                    type="file"
                    id="formFile"
                    onChange={(e) => {
                      setImage(e.target.files[0]);
                    }}
                  />
                </div>
              </div>
            </div>

            <div className="form-floating">
              <textarea
                className="form-control"
                placeholder="Car Description"
                id="floatingCarDescription"
                style={{ height: "100px" }}
                onChange={(e) => {
                  setDescription(e.target.value);
                }}
              ></textarea>
              <label htmlFor="floatingCarDescription">Description</label>
            </div>

            <button type="submit" className="btn btn-primary mt-3">
              Sell Car
            </button>
          </form>
        </div>
      </div>
    </div>
  );
}
