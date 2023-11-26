import React, { useEffect, useState } from "react";
import { useContext } from "react";
import { data } from "../App";

export default function CustomerRareCarBody() {
  const getMaxCarCost = (totalData) => {
    return totalData
      ? totalData.reduce((maxCost, car) => {
          return car.cost > maxCost ? car.cost : maxCost;
        }, 0)
      : 0;
  };

  let [totalData, setTotalData] = useState();
  let [maxCarCost, setMaxCarCost] = useState(getMaxCarCost());
  let [carType, setCarType] = useState("rare");
  let [carFilterCost, setCarFilterCost] = useState(0);
  let [mainData, setMainData] = useState();

  const customer = useContext(data);

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          `http://localhost:8090/getCarsByType/${carType}`
        );
        if (response.ok) {
          const result = await response.json();
          const maxCostForType = getMaxCarCost(result);
          setCarFilterCost(maxCostForType);
          setMaxCarCost(0);
          setMainData(result);
          setTotalData(result);
        } else {
          console.error("Failed to fetch data");
        }
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, [carType]);

  useEffect(() => {
    setMaxCarCost(getMaxCarCost(totalData));
  }, [totalData]);

  useEffect(() => {
    mainData &&
      setMainData(totalData.filter((car) => car.cost <= carFilterCost));
  }, [carFilterCost, totalData]);

  const addToCart = async (carId) => {
    try {
      const response = await fetch("http://localhost:8090/addToCart", {
        method: "POST",
        body: JSON.stringify({
          customerId: customer.customerId,
          carId: carId,
        }),
        headers: {
          "content-type": "application/json; charset=UTF-8",
        },
      });
      if (response.ok) {
        const data = response.json();
        if (data) {
          alert("Added to cart");
        } else {
          alert("Failed to add into cart !!!");
        }
      } else {
        console.error("Failed to add to cart");
      }
    } catch (error) {
      console.error("Error adding to cart:", error);
    }
  };

  const buyButtonClick = (carId) => {
    addToCart(carId);
  };

  return (
    <div className="container mt-5 mb-5">
      <div className="row">
        <div className="col-lg-4 mt-3 mb-3">
          <div className="form-floating">
            <select
              className="form-select"
              id="floatingFuel"
              aria-label="Floating label select example"
              onChange={(e) => {
                setCarType(e.target.value);
              }}
            >
              <option value="rare">Rare Cars</option>
              <option value="new">New Cars</option>
              <option value="used">Used Cars</option>
            </select>
            <label htmlFor="floatingFuel">Select Car Type</label>
          </div>
        </div>

        <div className="col-lg-4 mt-3 mb-3">
          <label htmlFor="customRange1" className="form-label">
            Price Range ( $ 0 - $ {carFilterCost.toLocaleString("en-IN")} )
          </label>
          <input
            type="range"
            className="form-range"
            id="customRange1"
            min={0}
            value={carFilterCost}
            step={50000}
            max={maxCarCost}
            onChange={(e) => {
              setCarFilterCost(Number(e.target.value));
            }}
          />
        </div>
      </div>

      <div className="row">
        {mainData &&
          mainData.map((car, index) => (
            <div className="col-md-4 mb-4" key={index}>
              <div className="card mt-3">
                <img
                  src={`http://localhost:8090/uploads/${car.carImage1}`}
                  className="card-img-top mx-auto"
                  style={{ aspectRatio: 3 / 2 }}
                  alt="..."
                />
                <div className="card-body">
                  <h5 className="card-title">
                    <div className="row">
                      <div className="col-12 text-truncate">
                        {car.company} - {car.modelName}
                      </div>
                    </div>
                  </h5>
                  <p className="card-text">
                    Year Model : {car.yearModel}
                    <br />
                    Fuel type: {car.fuelType}
                    <br />
                    <div className="row">
                      <div className="col-12 text-truncate">
                        Description: {car.carDescription}
                      </div>
                    </div>
                    Cost: $ {car.cost.toLocaleString("en-IN")}
                    <br />
                  </p>
                  <button
                    className="btn btn-primary"
                    onClick={() => {
                      buyButtonClick(car.carId);
                    }}
                  >
                    Add to Cart
                  </button>
                </div>
              </div>
            </div>
          ))}
      </div>
    </div>
  );
}
