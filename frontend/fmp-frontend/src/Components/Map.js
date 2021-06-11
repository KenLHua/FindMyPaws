import React, { Component } from 'react'

class Map extends Component {
    constructor(props) {
        super(props)
        this.state = {

        }
    }

    render() {
        return (
            <div className="container border-left border-top border-bottom border-dark rounded-left p-0 m-0">
                <div className="row p-0 m-0">
                    <div className="col p-0 m-0">
                        <iframe src="https://www.google.com/maps/embed?pb=!1m14!1m12!1m3!1d190256.09898778822!2d-87.87204602780633!3d41.83364785091003!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!5e0!3m2!1sen!2sus!4v1623251766480!5m2!1sen!2sus" width="100%" height="557" frameBorder="0" allowFullScreen="" loading="lazy"></iframe>
                    </div>
                </div>
            </div>
        )
    }
}

export default Map