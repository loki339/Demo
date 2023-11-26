import React from "react";
import { data } from "../App";
import { useContext } from "react";
import { useState } from "react";
import { useEffect } from "react";

export default function ReviewsBody() {
  let customer = useContext(data);
  let [reviewData, setReviewData] = useState();
  let [carData, setCarData] = useState();
  let [review1, setReview1] = useState();
  let [editedRating, setEditedRating] = useState();
  let [editedReview, setEditedReview] = useState();

  const fetchData = async () => {
    try {
      const response = await fetch(
        `http://localhost:8090/getReviewsByCustomerId/${customer.customerId}`
      );
      if (response.ok) {
        const result = await response.json();
        setReviewData(result);
      } else {
        setReviewData("NoReviews");
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  useEffect(() => {
    fetchData();
  }, []);

  const handleViewCar = async (carId) => {
    try {
      const response = await fetch(`http://localhost:8090/getCar/${carId}`);
      if (response.ok) {
        const result = await response.json();
        setCarData(result);
      } else {
        console.error("Failed to fetch data");
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const handleEditReview = async (feedbackId) => {
    try {
      const response = await fetch(
        `http://localhost:8090/getReview/${feedbackId}`
      );
      if (response.ok) {
        const result = await response.json();
        setEditedRating(result.rating);
        setEditedReview(result.review);
        setReview1(result);
      } else {
        console.error("Failed to fetch data");
      }
    } catch (error) {
      console.error("Error fetching data:", error);
    }
  };

  const handleDelete = async (feedbackId) => {
    try {
      const response = await fetch(
        `http://localhost:8090/deleteReview/${feedbackId}`,
        {
          method: "DELETE",
          headers: {
            "Content-Type": "application/json",
          },
        }
      );
      if (response.ok) {
        fetchData();
      } else {
        console.error("Failed to delete Review");
      }
    } catch (error) {
      console.error("Error deleting Review:", error);
    }
  };

  const editReview = async (feedbackId) => {
    try {
      let date = new Date();
      const response = await fetch(
        `http://localhost:8090/updateReview/${feedbackId}`,
        {
          method: "PUT",
          body: JSON.stringify({
            feedbackId: feedbackId,
            customerId: customer.customerId,
            carId: review1.carId,
            feedbackDate: date,
            review: editedReview,
            rating: editedRating,
          }),
          headers: {
            "content-type": "application/json; charset=UTF-8",
          },
        }
      );
      if (response.ok) {
        const data = await response.json();
        if (data) {
          fetchData();
          alert("Review Updated");
        } else {
          alert("Failed to update review !!!");
        }
      } else {
        console.error("Failed to update review");
      }
    } catch (error) {
      console.error("Error in updating review:", error);
    }
  };

  return (
    reviewData &&
    (reviewData === "NoReviews" ? (
      <div className="container mt-5 mb-5">
        <h1 className="text-light">No Data Found...</h1>
      </div>
    ) : (
      <div className="container mt-5 mb-5 text-light">
        <h4 className="mb-4">Car Reviews...</h4>
        <table className="table table-hover table-bordered border-secondary mt-4">
          <thead>
            <tr>
              <th scope="col">FeedbackID</th>
              <th scope="col">CarID</th>
              <th scope="col">FeedbackDate</th>
              <th scope="col">Review</th>
              <th scope="col">Rating</th>
              <th scope="col" className="text-center">
                ViewCar
              </th>
              <th scope="col" className="text-center">
                EditReview
              </th>
              <th scope="col" className="text-center">
                DeleteReview
              </th>
            </tr>
          </thead>
          <tbody>
            {reviewData.map((review, index) => (
              <tr key={index}>
                <th scope="row">{review.feedbackId}</th>
                <td>{review.carId}</td>
                <td>{review.feedbackDate.slice(0, 10)}</td>
                <td>{review.review}</td>
                <td>{review.rating}</td>
                <td className="text-center">
                  <button
                    className="btn btn-sm btn-primary"
                    data-bs-toggle="modal"
                    data-bs-target={`#exampleModal${review.carId}`}
                    onClick={() => {
                      handleViewCar(review.carId);
                    }}
                  >
                    View Car
                  </button>
                  <div
                    className="modal fade"
                    id={`exampleModal${review.carId}`}
                    tabIndex="-1"
                    aria-labelledby="exampleModalLabel"
                    aria-hidden="true"
                  >
                    <div className="modal-dialog text-start">
                      <div className="modal-content">
                        <div className="modal-header">
                          <h1
                            className="modal-title fs-5"
                            id="exampleModalLabel"
                          >
                            Car Details
                          </h1>
                          <button
                            type="button"
                            className="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                          ></button>
                        </div>
                        <div className="modal-body">
                          <div className="card shadow-lg border-dark mt-3">
                            {carData && (
                              <img
                                src={`http://localhost:8090/uploads/${carData.carImage1}`}
                                className="card-img-top mx-auto"
                                style={{ aspectRatio: 3 / 2 }}
                                alt="..."
                              />
                            )}
                            <div className="card-body">
                              {carData && (
                                <div>
                                  <h5 className="card-title">
                                    {carData.company} - {carData.modelName}
                                  </h5>
                                  <p className="card-text">
                                    Year Model: {carData.yearModel}
                                    <br />
                                    Fuel type: {carData.fuelType}
                                    <br />
                                    Description: {carData.carDescription}
                                    <br />
                                    Cost: ${" "}
                                    {carData.cost.toLocaleString("en-IN")}
                                    <br />
                                  </p>
                                </div>
                              )}
                            </div>
                          </div>
                        </div>
                        <div className="modal-footer">
                          <button
                            type="button"
                            className="btn btn-secondary"
                            data-bs-dismiss="modal"
                          >
                            Close
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </td>
                <td className="text-center">
                  <button
                    className="btn btn-sm btn-warning"
                    data-bs-toggle="modal"
                    data-bs-target={`#exampleModal2${review.feedbackId}`}
                    onClick={() => {
                      handleEditReview(review.feedbackId);
                    }}
                  >
                    Edit Review
                  </button>
                  <div
                    className="modal fade"
                    id={`exampleModal2${review.feedbackId}`}
                    tabIndex="-1"
                    aria-labelledby="exampleModalLabel"
                    aria-hidden="true"
                  >
                    <div className="modal-dialog text-start">
                      <div className="modal-content">
                        <div className="modal-header">
                          <h1
                            className="modal-title fs-5"
                            id="exampleModalLabel"
                          >
                            Edit Review
                          </h1>
                          <button
                            type="button"
                            className="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                          ></button>
                        </div>
                        <div className="modal-body">
                          <div className="container">
                            {review1 && (
                              <div className="form-floating">
                                <select
                                  className="form-select"
                                  id="floatingSelect"
                                  aria-label="Floating label select example"
                                  value={editedRating}
                                  onChange={(e) => {
                                    setEditedRating(e.target.value);
                                  }}
                                >
                                  {/* <option selected>Give rating</option> */}
                                  <option value="1">1</option>
                                  <option value="2">2</option>
                                  <option value="3">3</option>
                                  <option value="4">4</option>
                                  <option value="5">5</option>
                                </select>
                                <label htmlFor="floatingSelect">
                                  Car Rating
                                </label>
                              </div>
                            )}
                            {review1 && (
                              <div className="form-floating mt-3">
                                <textarea
                                  className="form-control"
                                  placeholder="Review"
                                  id="floatingTextarea2"
                                  style={{ height: "100px" }}
                                  value={editedReview}
                                  onChange={(e) => {
                                    setEditedReview(e.target.value);
                                  }}
                                ></textarea>
                                <label htmlFor="floatingTextarea2">
                                  Review
                                </label>
                              </div>
                            )}
                          </div>
                        </div>
                        <div className="modal-footer">
                          <button
                            type="button"
                            className="btn btn-primary"
                            data-bs-dismiss="modal"
                            onClick={() => {
                              editReview(review1.feedbackId);
                            }}
                          >
                            Edit Review
                          </button>
                        </div>
                      </div>
                    </div>
                  </div>
                </td>

                <td className="text-center">
                  <button
                    className="btn btn-sm btn-danger"
                    data-bs-toggle="modal"
                    data-bs-target={`#exampleModal1${review.carId}`}
                    onClick={() => {
                      handleViewCar(review.carId);
                    }}
                  >
                    Delete Review
                  </button>
                  <div
                    className="modal fade"
                    id={`exampleModal1${review.carId}`}
                    tabIndex="-1"
                    aria-labelledby="exampleModalLabel"
                    aria-hidden="true"
                  >
                    <div className="modal-dialog text-start">
                      <div className="modal-content">
                        <div className="modal-header">
                          <h1
                            className="modal-title fs-5"
                            id="exampleModalLabel"
                          >
                            Delete Review
                          </h1>
                          <button
                            type="button"
                            className="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                          ></button>
                        </div>
                        <div className="modal-body">
                          <p>
                            Do you want to <b className="text-danger">DELETE</b>{" "}
                            the review ?
                          </p>
                        </div>
                        <div className="modal-footer">
                          <button
                            type="button"
                            className="btn btn-danger"
                            data-bs-dismiss="modal"
                            onClick={() => {
                              handleDelete(review.feedbackId);
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
    ))
  );
}
