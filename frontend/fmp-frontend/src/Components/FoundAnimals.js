import React, { Component } from 'react'
import Animal from './Animal'

class FoundAnimals extends Component {
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
                        <h3>Found Animals<hr /></h3>
                    </div>
                </div>
                <div className="row" id="foundAnimals">
                    <Animal name="Fido" dateFound="06/09/2021" currentLocation="test address" />
                    <Animal name="Fido" dateFound="06/09/2021" currentLocation="test address" />
                    <Animal name="Fido" dateFound="06/09/2021" currentLocation="test address" />
                    <Animal name="Fido" dateFound="06/09/2021" currentLocation="test address" />
                    <Animal name="Fido" dateFound="06/09/2021" currentLocation="test address" />
                    <Animal name="Fido" dateFound="06/09/2021" currentLocation="test address" />
                    <Animal name="Fido" dateFound="06/09/2021" currentLocation="test address" />
                    <Animal name="Fido" dateFound="06/09/2021" currentLocation="test address" />
                </div>
            </div>
        )
    }
}

export default FoundAnimals