import React, { Component } from 'react'
import Modal from "./Modal"

class Animal extends Component {
    constructor(props) {
        super(props)
        this.state = {
            id: props.id,
            name: props.name,
            owner: props.owner,
            gender: props.gender,
            height: props.height,
            weight: props.weight,
            color: props.color,
            species: props.species,
            breed: props.breed,
            description: props.description,
            tag: props.tag,
            phone: props.phone,
            email: props.email,
            dateLost: props.dateLost,
            lastSeen: props.lastSeen,
            dateFound: props.dateFound,
            currentLocation: props.currentLocation,
            show: false
        };
        this.showModal = this.showModal.bind(this)
        this.hideModal = this.hideModal.bind(this)
        this.getGender = this.getGender.bind(this)
        this.genderJSX = this.getGender(this.state.gender)
        this.getJSX = this.getJSX.bind(this)
    }

    showModal = () => {
        this.setState({ show: true })
        console.log(this.state.id)
    }

    hideModal = () => {
        this.setState({ show: false })
    }

    getGender(gender) {
        if (gender) {
            return (
                <h5>Female</h5>
            )
        }
        else {
            return (
                <h5>Male</h5>
            )
        }
    }

    // not functional
    getJSX(str) {
        console.log(str)
        if (typeof str !== null && typeof str !== undefined && str !== "") {
            return (
                <h5>{str}</h5>
            )
        }
        else {
            return (
                <h5>Not Found</h5>
            )
        }
    }

    render() {
        if (this.state.dateFound == "") {
            return (
                <div className="col-6">
                    <button className="btn p-0 mb-3 ml-0 mr-0" onClick={this.showModal}>
                        <div className="card border border-dark m-0">
                            <div className="card-header border-bottom border-dark text-center pb-0"><h3>{this.state.name}</h3></div>
                            <div className="card-body">
                                <div className="container">
                                    <div className="row">
                                        <div className="col-4 rounded p-0">
                                            <img className="img-fluid rounded" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg" />
                                        </div>
                                        <div className="col-8">
                                            <div className="container">
                                                <div className="row">
                                                    <div className="col-12 text-left">
                                                        Date Lost:
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col-12 text-right">
                                                        <p>{this.state.dateLost}</p>
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col-12 text-left">
                                                        Last Seen:
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col-12 text-right">
                                                        <p>{this.state.lastSeen}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </button>
                    <div>
                        <Modal show={this.state.show} handleClose={this.hideModal}>
                            <div className="container mt-3">
                                <div className="row">
                                    <div className="col-12">
                                        <h2>{this.state.name}</h2>
                                        <hr />
                                    </div>
                                    <div className="col-6">
                                        <img className="img-fluid rounded" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg"></img>
                                    </div>
                                    <div className="col-6">
                                        <div className="container">
                                            <div className="row">
                                                <div className="col-3 text-left">
                                                    <h5>
                                                        Species:
                                                    </h5>
                                                    <h5>
                                                        Breed:
                                                    </h5>
                                                    <h5>
                                                        Color:
                                                    </h5>
                                                    <h5>
                                                        Gender:
                                                    </h5>
                                                    <h5>
                                                        Height:
                                                    </h5>
                                                    <h5>
                                                        Weight:
                                                    </h5>
                                                    <h5>
                                                        Description:
                                                    </h5>
                                                </div>
                                                <div className="col-9 text-right">
                                                    <h5>
                                                        {this.getJSX(this.state.species)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.breed)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.color)}
                                                    </h5>
                                                    <h5>
                                                        {this.genderJSX}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.height)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.weight)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.description)}
                                                    </h5>
                                                </div>
                                                <div className="col-12">
                                                    <hr />
                                                    <h3>
                                                        Owner Information
                                                    </h3>
                                                    <hr />
                                                </div>
                                                <div className="col-3 text-left">
                                                    <h5>
                                                        Phone: 
                                                    </h5>
                                                    <h5>
                                                        Email: 
                                                    </h5>
                                                </div>
                                                <div className="col-9 text-right">
                                                    <h5>
                                                        {this.getJSX(this.state.phone)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.email)}
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </Modal>
                    </div>
                </div>

            )
        }
        else {
            return (
                <div className="col-6">
                    <button className="btn p-0 mb-3 ml-0 mr-0" onClick={this.showModal}>
                        <div className="card border border-dark m-0">
                            <div className="card-header border-bottom border-dark text-center pb-0"><h3>{this.state.name}</h3></div>
                            <div className="card-body">
                                <div className="container">
                                    <div className="row">
                                        <div className="col-4 rounded p-0">
                                            <img className="img-fluid rounded" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg" />
                                        </div>
                                        <div className="col-8">
                                            <div className="container">
                                                <div className="row">
                                                    <div className="col-12 text-left">
                                                        Date Found:
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col-12 text-right">
                                                        <p>{this.state.dateFound}</p>
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col-12 text-left">
                                                        Current Location:
                                                    </div>
                                                </div>
                                                <div className="row">
                                                    <div className="col-12 text-right">
                                                        <p>{this.state.currentLocation}</p>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </button>
                    <div>
                        <Modal show={this.state.show} handleClose={this.hideModal}>
                            <div className="container mt-3">
                                <div className="row">
                                    <div className="col-12">
                                        <h2>{this.state.name}</h2>
                                        <hr />
                                    </div>
                                    <div className="col-6">
                                        <img className="img-fluid rounded" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg"></img>
                                    </div>
                                    <div className="col-6">
                                        <div className="container">
                                            <div className="row">
                                                <div className="col-3 text-left">
                                                    <h5>
                                                        Species:
                                                    </h5>
                                                    <h5>
                                                        Breed:
                                                    </h5>
                                                    <h5>
                                                        Color:
                                                    </h5>
                                                    <h5>
                                                        Gender:
                                                    </h5>
                                                    <h5>
                                                        Height:
                                                    </h5>
                                                    <h5>
                                                        Weight:
                                                    </h5>
                                                    <h5>
                                                        Description:
                                                    </h5>
                                                </div>
                                                <div className="col-9 text-right">
                                                    <h5>
                                                        {this.getJSX(this.state.species)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.breed)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.color)}
                                                    </h5>
                                                    <h5>
                                                        {this.genderJSX}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.height)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.weight)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.description)}
                                                    </h5>
                                                </div>
                                                <div className="col-12">
                                                    <hr />
                                                    <h3>
                                                        Owner Information
                                                    </h3>
                                                    <hr />
                                                </div>
                                                <div className="col-3 text-left">
                                                    <h5>
                                                        Phone: 
                                                    </h5>
                                                    <h5>
                                                        Email: 
                                                    </h5>
                                                </div>
                                                <div className="col-9 text-right">
                                                    <h5>
                                                        {this.getJSX(this.state.phone)}
                                                    </h5>
                                                    <h5>
                                                        {this.getJSX(this.state.email)}
                                                    </h5>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </Modal>
                    </div>
                </div>
            )
        }
    }
}

export default Animal