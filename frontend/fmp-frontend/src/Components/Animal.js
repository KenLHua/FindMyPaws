import React, { Component } from 'react'

class Animal extends Component {
    constructor(props) {
        super(props)
        this.state = {
            name: props.name,
            dateLost: props.dateLost,
            lastSeen: props.lastSeen,
            dateFound: props.dateFound,
            currentLocation: props.currentLocation
        }
    }

    render() {
        if (this.state.dateFound == "") {
            return (
                <div className="col-6">
                    <button className="btn p-0 mb-3 ml-0 mr-0">
                        <div className="card border border-dark m-0">
                            <div className="card-header border-bottom border-dark text-center pb-0"><h3>{this.state.name}</h3></div>
                            <div className="card-body">
                                <div className="container">
                                    <div className="row">
                                        <div className="col-4 rounded p-0">
                                            <img className="img-fluid" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg" />
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
                </div>
            )
        }
        else {
            return (
                <div className="col-6">
                    <button className="btn p-0 mb-3 ml-0 mr-0">
                        <div className="card border border-dark m-0"> 
                            <div className="card-header border-bottom border-dark text-center pb-0"><h3>{this.state.name}</h3></div>
                            <div className="card-body">
                                <div className="container">
                                    <div className="row">
                                        <div className="col-4 rounded p-0">
                                            <img className="img-fluid" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg" />
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
                </div>
            )
        }
    }
}

export default Animal