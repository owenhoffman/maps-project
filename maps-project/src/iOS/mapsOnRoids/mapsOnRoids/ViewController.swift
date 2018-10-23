//
//  ViewController.swift
//  mapsOnRoids
//
//  Created by Marquel Hendricks on 10/23/18.
//  Copyright © 2018 Marquel Hendricks. All rights reserved.
//

import UIKit
import GoogleMaps

class ViewController: UIViewController {
    
    var mapView: GMSMapView!
    var strLat = 0.0
    var strLong = 0.0
    var camera: GMSCameraPosition?

    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
        layoutViews()
    }

    override func didReceiveMemoryWarning() {
        super.didReceiveMemoryWarning()
        // Dispose of any resources that can be recreated.
    }
    
    func layoutViews() {
        strLong = -76.942554
        strLat = 38.986918
        camera = GMSCameraPosition.camera(withLatitude: strLat, longitude: strLong, zoom: 15.0)
        mapView = GMSMapView.map(withFrame: CGRect.zero, camera: camera!)
        mapView.isMyLocationEnabled = true
        mapView.settings.myLocationButton = true
        view = mapView
    }


}

