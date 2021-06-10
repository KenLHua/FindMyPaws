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
            <button className="btn p-0 mb-3 ml-0 mr-0">
                <div className="card border border-dark m-0">
                    <div className="card-header border-bottom border-dark text-center pb-0"><h3>{this.state.animalName}</h3></div>
                    <div className="card-body">
                        <div className="container">
                            <div className="row">
                                <div className="col-3 m-0 p-0 border border-dark">
                                    <img className="img-fluid" src="https://www.guidedogs.org/wp-content/uploads/2019/11/website-donate-mobile.jpg" />
                                </div>
                                <div className="col-1"></div>
                                <div className="col-4 m-0 p-0">
                                    <div className="container">
                                        <div className="row">
                                            <div className="col-12 text-center">
                                                <h4>Date Seen:</h4>
                                            </div>
                                            <div className="col-12 text-center">
                                                <h5>{this.state.date}</h5>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div className="col-4 m-0 p-0">
                                    <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d25155.85530476795!2d-84.5327028!3d37.9892184!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sus!4v1623255635392!5m2!1sen!2sus" width="250" height="250" frameborder="0" allowfullscreen="" loading="lazy"></iframe>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </button>
        )
    }
}

export default Sighting