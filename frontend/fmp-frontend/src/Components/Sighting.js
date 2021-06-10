import React, { Component } from 'react'

class Sighting extends Component {
    constructor(props) {
        super(props)
        this.state = {
            animalName: props.animalName,
            location: props.location,
            date: props.date
        }
    }

    render() {
        return (
            <div className="col-6">
            <button className="btn p-0 mb-3 ml-0 mr-0">
                <div className="card border border-dark m-0">
                    <div className="card-header border-bottom border-dark text-center pb-0"><h3>{this.state.animalName}</h3></div>
                    <div className="card-body">
                        <div className="container">
                            <div className="row">
                                <div className="col-6 m-0 p-0 border border-dark">
                                    <img className="img-fluid" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg" />
                                </div>
                                <div className="col-6 m-0 p-0">
                                    <div className="container">
                                        <div className="row">
                                            <div className="col-12 text-center">
                                                <h4>Last Seen:</h4><br />
                                            </div>
                                            <div className="col-12 text-center">
                                                <h5>{this.state.date}</h5><br />
                                            </div>
                                            <div className="col-12 text-center">
                                                <h5>{this.state.location}</h5>
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

export default Sighting