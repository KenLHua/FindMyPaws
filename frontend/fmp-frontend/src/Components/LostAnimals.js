import React, { Component } from 'react'
import Animal from './Animal'

class LostAnimals extends Component {
    constructor(props) {
        super(props)
        this.state = {

        }
    }

    render () {
        return (
            <div className="container ml-0 border border-dark rounded bg-light">
                <div className="row">
                    <div className="col-12 mt-3">
                        <h3>Lost Animals<hr /></h3>
                    </div>
                </div>
                <div className="row mh-30" id="lostAnimals">
                    <div className="col-6">
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                    </div>
                    <div className="col-6">
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                    </div>
                    <div className="col-6">
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                    </div>
                    <div className="col-6">
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                    </div>
                    <div className="col-6">
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                        <Animal name="Fido" dateLost="06/01/2021" lastSeen="27 W 142nd St, Harvey, Illinois(IL), 60426" dateFound="" />
                    </div>
                </div>
            </div>
        )
    }
}

export default LostAnimals